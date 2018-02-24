package com.sine.yys.simulation.component;

import com.sine.yys.buff.debuff.Debuff;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.event.*;
import com.sine.yys.info.AttackInfo;
import com.sine.yys.info.PctEffect;
import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.rule.CalcDam;
import com.sine.yys.rule.CalcEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.Iterator;
import java.util.logging.Logger;

public class ControllerImpl implements Controller {
    private final Logger log = Logger.getLogger(getClass().toString());
    private final BaseCamp camp0, camp1;

    public ControllerImpl(BaseCamp camp0, BaseCamp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    @Override
    public Camp getCamp(Entity entity) {
        return ((EntityImpl) entity).getCamp();
    }

    @Override
    public void attack(Entity self0, Entity target0, AttackInfo attackInfo) {
        EntityImpl self = (EntityImpl) self0;
        EntityImpl target = (EntityImpl) target0;
        if (target.isDead())  // XXX 只是有时会出现目标已死。有更好的逻辑？
            return;

        self.eventController.trigger(new AttackEvent(this, self, target));

        // XXXXX 像这种每次都调用是不是不好、太慢
        this.getCamp(target).getEventController().triggerOff(new BeAttackEvent(this));
        target.getEventController().triggerOff(new BeAttackEvent(this));

        // 1.
        final boolean critical = RandUtil.success(self.getCritical());
        if (critical)
            log.info(Msg.info(self, "暴击"));
        double damage = CalcDam.expect(self, target, attackInfo, critical);

        //3.
        int remain = breakShield(target, (int) damage);

        // 4.
        if (remain != 0) {
            damage = remain;

            PreDamageEvent event = new PreDamageEvent(this, self, target);
            self.eventController.trigger(event);
            damage *= event.getCoefficient();

            // 附加效果
            self.eventController.trigger(new DamageEvent(this, self, target));
            log.info(Msg.damage(self, target, (int) damage));
            doDamage(target, (int) damage);
            if (target.getLifeInt() == 0)
                log.info(Msg.vector(self, "击杀", target, ""));

            if (critical) {
                target.getEventController().trigger(new BeCriticalEvent(this, target, self));
                self.eventController.trigger(new CriticalEvent(this, self, target));
            }
        } else {
            log.info(Msg.noDamage(self, target));
        }
    }

    @Override
    public void realDamage(Entity self0, Entity target0, double damage) {
        EntityImpl self = (EntityImpl) self0;
        EntityImpl target = (EntityImpl) target0;
        if (target.isDead())
            return;

        self.eventController.trigger(new AttackEvent(this, self, target));

        // 2.
        int remain = breakShield(target, (int) damage);

        // 3.
        if (remain != 0) {
            // 附加效果
            self.eventController.trigger(new DamageEvent(this, self, target));
            log.info(Msg.damage(self, target, (int) damage));
            doDamage(target, (int) damage);
            if (target.getLifeInt() == 0)
                log.info(Msg.vector(self, "击杀", target, ""));
        } else {
            log.info(Msg.noDamage(self, target));
        }
    }

    /**
     * 直接造成伤害，不再计算任何buff加成效果，可被护盾减免，不受椒图分摊。
     * <p>
     * 用于实现持续伤害效果。
     * 未来也可实现椒图分摊后的伤害。
     *
     * @param damage 伤害值。
     */
    private void directDamage(EntityImpl self, int damage) {
        damage = breakShield(self, damage);
        doDamage(self, damage);
    }

    /**
     * 直接减少目标生命，触发{@link BeDamageEvent}事件。
     * <p>
     * 未来可能进行死亡处理，如匣中少女的被动，立即复活并回复状态，不会计算击杀。
     */
    private void doDamage(Entity target, int damage) {
        if (target.getLifeInt() > damage) {
            double src = target.getLife();
            target.setLife(target.getLifeInt() - damage);
            double dst = target.getLife();
            target.getEventController().trigger(new BeDamageEvent(src, dst));
        } else {
            target.setLife(0);
            target.getCamp().getPosition(target).setDead(true);
            slog.info(Msg.info(target, "死亡"));
        }
    }

    /**
     * 进行护盾的伤害减免计算。
     *
     * @return 剩余伤害。
     */
    private int breakShield(EntityImpl target, int damage) {
        Iterator<Shield> iterator = target.buffController.getShields().iterator();
        for (; iterator.hasNext(); ) {
            Shield shield = iterator.next();
            damage = shield.doDamage(damage);
            if (damage == -1)
                break;
            target.buffController.removeShield(shield);
            log.info(Msg.info(target, shield.getName() + " 被击破"));
        }
        if (damage == -1)
            damage = 0;
        return damage;
    }

    @Override
    public void randomGrab(Entity self, Entity target, double pct) {
        if (RandUtil.success(pct)) {
            int num = target.getFireRepo().grabFire(1);
            if (num > 0)
                log.info(Msg.vector(self, "吸取", target, num + " 点鬼火"));
            self.getFireRepo().addFire(num);
        }
    }

    @Override
    public void applyDebuff(Entity self, PctEffect effect, Entity target, Debuff debuff) {
        if (RandUtil.success(CalcEffect.pct(effect.getPct(), self.getEffectHit()))) {
            log.info(Msg.trigger(self, effect));
            if (RandUtil.success(CalcEffect.hitPct(target.getEffectDef()))) {
                log.info(Msg.info(target, "获得负面效果 " + debuff.getName()));
                target.getBuffController().addIBuff(debuff);
            } else {
                log.info(Msg.info(target, "抵抗了负面效果 " + debuff.getName()));
            }
        }
    }

    @Override
    public void xieZhan(Entity self, Entity target) {
        // 目标死亡则随机攻击另一个目标
        Camp own = getCamp(self);
        if (own.contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "在", self, "己方阵营中，随机协战"));
            target = own.getOpposite().randomTarget();
        }
        if (target != null)
            ((EntityImpl) self).getCommonAttack().xieZhan(this, self, target);
    }

    @Override
    public void clear() {
        camp0.getEventController().setState(BeAttackEvent.class, true);
        camp1.getEventController().setState(BeAttackEvent.class, true);
        for (EntityImpl entity : camp0.getAllAlive2()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
        for (EntityImpl entity : camp1.getAllAlive2()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
    }
}
