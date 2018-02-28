package com.sine.yys.simulation.component;

import com.sine.yys.buff.shield.Shield;
import com.sine.yys.event.*;
import com.sine.yys.info.AttackInfo;
import com.sine.yys.inter.*;
import com.sine.yys.rule.CalcDam;
import com.sine.yys.rule.CalcEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * 主要战场逻辑。
 */
public class ControllerImpl implements Controller {
    private final Logger log = Logger.getLogger(getClass().getName());
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
    public void attack(Entity self0, Entity target0, AttackInfo attackInfo, Collection<DebuffEffect> debuffEffects) {
        EntityImpl self = (EntityImpl) self0;
        EntityImpl target = (EntityImpl) target0;
        if (target.isDead())  // XXX 只是有时会出现目标已死。有更好 的逻辑？
            return;

        // XXX 关于触发时机
        if (debuffEffects != null)
            for (DebuffEffect debuffEffect : debuffEffects) {
                applyDebuff(self, target, debuffEffect);
            }

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
            log.info(Msg.damage(self, target, (int) damage, critical));
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

    public void directDamage(Entity self0, int damage) {
        if (self0.isDead())  // XXXX 每次都判断死亡是不是太难看
            return;
        EntityImpl self = (EntityImpl) self0;
        damage = breakShield(self, damage);
        log.info(Msg.info(self, "受到伤害 " + damage));
        doDamage(self, damage);
    }

    @Override
    public int cure(Entity target0, double src) {
        EntityImpl target = (EntityImpl) target0;
        final double pct = target.getBuffController().getReduceCurePct();
        final int count;
        if (pct != 0.0)
            log.info(Msg.info(target, "受到减疗 " + pct));
        count = (int) (src * (1 - pct));
        log.info(Msg.info(target, "受到治疗回复 " + count));
        target.addLife(count);
        return count;
    }


    /**
     * 直接减少目标生命，触发{@link BeDamageEvent}事件。
     * <p>
     * 未来可能进行死亡处理，如匣中少女的被动，立即复活并回复状态，不会计算击杀。
     */
    private void doDamage(EntityImpl target, int damage) {
        double src = target.getLife();
        final int life = target.reduceLife(damage);
        double dst = target.getLife();
        if (life != 0) {
            target.getEventController().trigger(new BeDamageEvent(this, src, dst));
        } else {
            target.getCamp().getPosition(target).setCurrent(null);
            log.info(Msg.info(target, "死亡"));
        }
    }

    /**
     * 进行护盾的伤害减免计算。
     *
     * @return 剩余伤害。
     */
    private int breakShield(EntityImpl target, int damage) {
        for (Shield shield : target.buffController.getShields()) {
            damage = shield.doDamage(damage);
            if (damage == -1)
                break;
            target.buffController.remove(shield);
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
    public void applyDebuff(Entity self, Entity target, DebuffEffect effect) {
        final double pct;
        final boolean involveHitAndDef = effect.involveHitAndDef();
        if (involveHitAndDef)
            pct = CalcEffect.pct(effect.getPct(), self.getEffectHit());
        else
            pct = effect.getPct();
        if (RandUtil.success(pct)) {
            log.info(Msg.trigger(self, effect));
            Debuff debuff = effect.getDebuff(self);
            if (involveHitAndDef) {  // XXX 确定 true || exc()不会执行后者的话可以简化。
                if (RandUtil.success(CalcEffect.hitPct(target.getEffectDef()))) {
                    log.info(Msg.info(target, "获得负面效果 " + debuff.getName()));
                    target.getBuffController().add(debuff);
                } else {
                    log.info(Msg.info(target, "抵抗了负面效果 " + debuff.getName()));
                }
            } else {
                log.info(Msg.info(target, "获得负面效果 " + debuff.getName()));
                target.getBuffController().add(debuff);
            }
        }
    }

    @Override
    public void xieZhan(Entity self, Entity target) {
        // 目标死亡则随机攻击另一个目标
        Camp own = getCamp(self);
        if (!own.getOpposite().contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "在", self, "己方阵营中，随机协战"));
            target = own.getOpposite().randomTarget();
        }
        if (target != null)
            ((EntityImpl) self).getCommonAttack().xieZhan(this, self, target);
    }

    /**
     * 一次“动作”结束后的逻辑。
     * <p>
     * 之前用于实现群体/多段攻击不重复计算，触发一次后会关闭BeAttackEvent事件。
     * 技能调用此函数以重置状态。
     */
    public void afterMovement() {
        camp0.getEventController().trigger(new AfterMovementEvent(this));
        camp1.getEventController().trigger(new AfterMovementEvent(this));

        // 重置事件状态
        camp0.getEventController().setState(BeAttackEvent.class, true);
        camp1.getEventController().setState(BeAttackEvent.class, true);
        for (EntityImpl entity : camp0.getAllAlive()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
        for (EntityImpl entity : camp1.getAllAlive()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
    }

    @Override
    public void actionChance(Entity self0) {
        EntityImpl self = (EntityImpl) self0;
        self.setPosition(1.0);
    }

    @Override
    public int calcCritical(Entity self, double src) {
        if (!RandUtil.success(self.getCritical()))
            return (int) src;
        log.info(Msg.info(self, "暴击"));
        return (int) (src * self.getCriticalDamage());
    }
}
