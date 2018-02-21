package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.model.EventController;
import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.component.model.event.*;
import com.sine.yys.simulation.component.model.shield.Shield;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.PctEffect;
import com.sine.yys.simulation.rule.CalcDam;
import com.sine.yys.simulation.rule.CalcEffect;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

import java.util.Iterator;
import java.util.logging.Logger;

public class ControllerImpl implements Controller {
    private final Logger log = Logger.getLogger(getClass().toString());

    @Override
    public Entity getSelf() {
        return self;
    }

    @Override
    public Camp getOwn() {
        return own;
    }

    @Override
    public Camp getEnemy() {
        return enemy;
    }

    private final BaseEntity self;
    private final BaseCamp own, enemy;

    public ControllerImpl(BaseEntity self, BaseCamp own, BaseCamp enemy) {
        this.self = self;
        this.own = own;
        this.enemy = enemy;
    }

    @Override
    public EventController getEventController() {
        return self.getEventController();
    }

    /**
     * 伤害逻辑：
     * 1. 由攻击、伤害系数、对方防御（忽略防御）计算。
     * 2. 根据双方buff进行增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，添加御魂效果。
     */
    @Override
    public void attack(Entity target0, AttackInfo attackInfo) {
        BaseEntity target = (BaseEntity) target0;
        if (target.isDead())  // XXX 只是有时会出现目标已死。有更好的逻辑？
            return;

        self.eventController.trigger(new AttackEvent(self, target));

        // XXXXX 像这种每次都调用是不是不好、太慢
        target.getCamp().getEventController().triggerOff(new BeAttackEvent());
        target.getEventController().triggerOff(new BeAttackEvent());

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

            PreDamageEvent event = new PreDamageEvent(self, target);
            self.eventController.trigger(event);
            damage *= event.getCoefficient();

            doDamage(target, (int) damage);

            if (critical) {
                target.getEventController().trigger(new BeCriticalEvent(target, self));
                self.eventController.trigger(new CriticalEvent(self, target));
            }
        } else {
            log.info(Msg.noDamage(self, target));
        }
    }

    /**
     * 目前只有针女伤害。
     * 1. 计算伤害。
     * 2. 根据旗帜buff增减。
     * 3. 破盾。
     * 4. 施加剩余伤害，附加效果（似乎有比如山童的眩晕）。
     */
    @Override
    public void realDamage(Entity target0, double maxByAttack, double maxPctByMaxLife) {
        BaseEntity target = (BaseEntity) target0;
        if (target.isDead())
            return;

        self.eventController.trigger(new AttackEvent(self, target));

        // 1.
        final double damage1 = self.getAttack() * maxByAttack;
        final double damage2 = target.getMaxLife() * maxPctByMaxLife;
        double damage = damage1 < damage2 ? damage1 : damage2;

        // 3.
        int remain = breakShield(target, (int) damage);

        // 4.
        if (remain != 0) {
            doDamage(target, (int) damage);
        } else {
            log.info(Msg.noDamage(self, target));
        }

    }

    /**
     * 把伤害施加到盾上。
     *
     * @return 剩余伤害。
     */
    private int breakShield(Entity target, int damage) {
        Iterator<Shield> iterator = target.getBuffController().getShields().iterator();
        for (; iterator.hasNext(); ) {
            Shield shield = iterator.next();
            damage = shield.doDamage(damage);
            if (damage == -1)
                break;
            target.getBuffController().removeShield(shield);
            log.info(Msg.info(target, shield.getName() + " 被击破"));
        }
        if (damage == -1)
            damage = 0;
        return damage;
    }

    private void doDamage(BaseEntity target, int damage) {
        log.info(Msg.damage(self, target, damage));
        if (target.getLifeInt() > damage) {
            double src = target.getLife();
            target.setLife(target.getLifeInt() - damage);
            double dst = target.getLife();
            target.getEventController().trigger(new BeDamageEvent(src, dst));
        } else {
            log.info(Msg.vector(self, "击杀", target, ""));
            target.setLife(0);
            target.getCamp().getPosition(target).setDead(true);
        }
        // FIXME 死后添加debuff会有问题？
        self.eventController.trigger(new DamageEvent(self, target));
    }

    @Override
    public void randomGrab(double pct, Entity target) {
        if (RandUtil.success(pct)) {
            int num = target.getFireRepo().grabFire(1);
            if (num > 0)
                log.info(Msg.vector(self, "吸取", target, num + " 点鬼火"));
            self.fireRepo.addFire(num);
        }
    }

    @Override
    public void applyDebuff(PctEffect effect, Entity target, Debuff debuff) {
        if (RandUtil.success(CalcEffect.pct(effect.getPct(), self.getEffectHit()))) {
            log.info(Msg.trigger(self, effect));
            if (RandUtil.success(CalcEffect.hitPct(target.getEffectDef()))) {
                log.info(Msg.info(target, "获得负面效果 " + debuff.getName()));
                target.getBuffController().addDebuff(debuff);
            } else {
                log.info(Msg.info(target, "抵抗了负面效果 " + debuff.getName()));
            }
        }
    }

    @Override
    public void xieZhan(Entity target) {
        // 目标死亡则随机攻击另一个目标
        if (!enemy.contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "不在", self, "敌对阵营中，随机协战"));
            target = enemy.randomTarget();
        }
        if (target != null)
            self.getCommonAttack().xieZhan(this, self, target);
    }

    @Override
    public void clear() {
        own.getEventController().setState(BeAttackEvent.class, true);
        enemy.getEventController().setState(BeAttackEvent.class, true);
        for (BaseEntity entity : own.getAllAlive2()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
        for (BaseEntity entity : enemy.getAllAlive2()) {
            entity.getEventController().setState(BeAttackEvent.class, true);
        }
    }
}
