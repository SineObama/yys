package com.sine.yys.impl;

import com.sine.yys.buff.buff.DispellableBuff;
import com.sine.yys.buff.control.ControlBuff;
import com.sine.yys.buff.control.ShuiMian;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.event.*;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.Callback;
import com.sine.yys.rule.CalcDam;
import com.sine.yys.rule.CalcEffect;
import com.sine.yys.rule.buff.Cure;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

public class ControllerImpl implements Controller {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final Camp camp0;
    private final Camp camp1;
    private final Queue<SingleAction> actions = new PriorityQueue<>();

    public ControllerImpl(Camp camp0, Camp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    private Camp getCamp(Entity entity) {
        return camp0.contain(entity) ? camp0 : camp1;
    }

    @Override
    public void addAction(int prior, Callback callback) {
        if (!actions.contains(new SingleAction(prior, callback)))
            actions.add(new SingleAction(prior, callback));
    }

    /**
     * @return 取出第一个动作。
     * @see #addAction(int, Callback)
     */
    public Callback pollAction() {
        final SingleAction poll = actions.poll();
        if (poll == null)
            return null;
        return poll.getCallback();
    }

    /**
     * 伤害逻辑：
     * 1. 由攻击、伤害系数、对方防御（忽略防御）计算。
     * 2. 根据双方buff进行增减。
     * 3. 破盾，施加剩余伤害，添加御魂效果。
     */
    @Override
    public void attack(Entity self, Entity target, AttackInfo attackInfo) {
        attack(self, target, attackInfo, new AttackTypeImpl());
    }

    @Override
    public void counter(Entity self, Entity target, AttackInfo attackInfo) {
        attack(self, target, attackInfo, new AttackTypeImpl(false, true));
    }

    private void attack(Entity self, Entity target, AttackInfo attackInfo, AttackType type) {
        if (target.isDead())  // 多段攻击目标可能中途死亡
            return;

        // XXX 关于触发时机
        for (DebuffEffect debuffEffect : attackInfo.getDebuffEffects()) {
            applyDebuff(self, target, debuffEffect);
        }

        // 1.
        final boolean critical = RandUtil.success(self.getCritical());
        if (critical)
            log.info(Msg.info(self, "暴击"));
        double damage = CalcDam.expect(self, target, attackInfo, critical);
        damage *= attackInfo.randomFloat();

        // 2.
        damage *= self.getDamageCoefficient();
        damage *= target.getBeDamageCoefficient();

        // 3.
        applyDamage(self, target, damage, critical, type);
    }

    /**
     * 普通伤害的施加（包括破盾）。
     * 破盾后计算御魂效果，进行伤害分摊。
     */
    @Override
    public void applyDamage(Entity self, Entity target, double damage, boolean critical, AttackType type) {
        // 破盾
        final int remain = breakShield(target, (int) damage);

        if (remain != 0) {
            damage = remain;

            damage *= self.getEventController().trigger(new PreDamageEvent(self, target)).getCoefficient();

            // 处理薙魂和涓流。未来考虑金鱼、小松丸躲避。
            final DamageShareEvent damageShareEvent = new DamageShareEvent(self, target, damage, new AttackTypeImpl(type));
            damage = target.getEventController().trigger(damageShareEvent).getLeft();

            // 附加效果
            self.getEventController().trigger(new DamageEvent(self, target));
        }

        self.getEventController().trigger(new AttackEvent(self, target));
        target.getEventController().trigger(new BeAttackEvent(target, self, type));

        if (remain != 0) {
            doDamage(self, target, (int) damage, type, critical);
            if (target.getLifeInt() == 0)
                log.info(Msg.vector(self, "击杀", target));

            // XXX 地藏像死亡后盾buff还在？
            if (critical) {
                target.getEventController().trigger(new BeCriticalEvent(target, self));
                self.getEventController().trigger(new CriticalEvent(self, target, new AttackTypeImpl(type)));
            }
        } else {
            log.info(Msg.noDamage(self, target));
        }
    }

    // 涓流死亡算击杀，薙魂不算（阴摩罗）
    // 注意与applyDamage的统一
    @Override
    public void directDamage(Entity self, Entity target, int damage, AttackType type) {
        damage = breakShield(target, damage);
        if (damage > 0) {
            if (!type.isJuanLiu()) {  // 薙魂可以再被涓流分摊，涓流后不再判断涓流
                final DamageShareEvent damageShareEvent = new DamageShareEvent(self, target, damage, new AttackTypeImpl(type));
                damage = (int) target.getEventController().trigger(damageShareEvent).getLeft();
            } else if (!type.isTiHun()) {
                self.getEventController().trigger(new JuanLiuDamageEvent(target));
            }
            doDamage(self, target, damage, type, false);
        }
    }

    @Override
    public void buffDamage(Entity self, Entity target, int damage) {
        damage = breakShield(target, damage);
        if (damage > 0)
            doDamage(self, target, damage, new AttackTypeImpl(true), false);
    }

    @Override
    public int cureWithoutCritical(Entity self, Entity target, double src) {
        double coefficient = target.getCureCoefficient();
        double cure = self.getEventController().trigger(new PreCureEvent()).getCure();
        coefficient = new Cure().calc(coefficient, cure);
        log.info(Msg.info(target, "治疗效果", coefficient));
        int count = (int) (src * coefficient);
        if (count <= 0)
            count = 1;
        target.addLife(count);
        target.getEventController().trigger(new BeCureEvent(count));
        return count;
    }

    @Override
    public int cure(Entity self, Entity target, double src) {
        return cureWithoutCritical(self, target, calcCritical(self, src));
    }

    /**
     * 直接减少目标生命，打醒睡眠，触发{@link LostLifeEvent}事件。
     * <p>
     * 目标死亡则触发{@linkplain KillEvent}、{@linkplain BeKillEvent}。
     * <p>
     * 毒伤会打醒睡眠。
     */
    private void doDamage(Entity self, Entity target, double damage, AttackType type, boolean critical) {
        log.info(Msg.damage(self, target, (int) damage, critical));
        final BeDamageEvent event = new BeDamageEvent(target, self, new AttackTypeImpl(type), damage);
        target.getEventController().trigger(event);
        damage = event.getDamage();
        if (event.isWake())
            target.getBuffController().remove(ShuiMian.class);
        final double src = target.getLife();
        final int srcLife = target.getLifeInt();
        final int life = target.reduceLife((int) damage);
        final double dst = target.getLife();
        target.getEventController().trigger(new LostLifeEvent(src, dst, srcLife - life));
        if (life == 0) {
            // 添加匣中少女逻辑，回复状态则退出
            target.getBuffController().clear();// XXX 匣中少女回复状态会不会保留buff？
            target.getEventController().clear();
            getCamp(target).getPosition(target).setCurrent(null);
            log.info(Msg.info(target, "死亡"));
            // 包括阎魔放小鬼
            self.getEventController().trigger(new KillEvent(self, target, type));
            self.getEventController().trigger(new BeKillEvent(target, self, type));
            target.getEventController().trigger(new DieEvent(target));
        }
    }

    /**
     * 进行护盾的伤害减免计算。
     *
     * @return 剩余伤害。
     */
    private int breakShield(Entity target, int damage) {
        for (Shield shield : target.getBuffController().getWithPrior(Shield.class)) {
            damage = shield.doDamage(damage);
            if (damage == -1)
                break;
            target.getBuffController().remove(shield);
            log.info(Msg.info(target, shield.getName(), "被击破"));
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
                log.info(Msg.vector(self, "吸取", target, num, "点鬼火"));
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
            IBuff debuff = effect.getDebuff(self);
            if (!involveHitAndDef || RandUtil.success(CalcEffect.hitPct(target.getEffectDef()))) {
                boolean effective = true;
                if (debuff instanceof ControlBuff)
                    effective = !target.getEventController().trigger(new BeforeControlEvent((ControlBuff) debuff)).isNotEffective();
                if (effective) {
                    log.info(Msg.info(target, "获得负面效果", debuff.getName()));
                    target.getBuffController().add(debuff);
                }
            } else {
                log.info(Msg.info(target, "抵抗了负面效果", debuff.getName()));
            }
        }
    }

    /**
     * 一次“动作”结束后的逻辑。
     * <p>
     * 之前用于实现群体/多段攻击不重复计算，触发一次后会关闭BeAttackEvent事件。
     * 技能调用此函数以重置状态。
     */
    @Override
    public void afterMovement() {
        camp0.getEventController().trigger(new AfterMovementEvent());
        camp1.getEventController().trigger(new AfterMovementEvent());

        // 重置事件状态
        camp0.getEventController().clear();
        camp1.getEventController().clear();
        for (Entity entity : camp0.getAllAlive()) {
            entity.getEventController().clear();
        }
        for (Entity entity : camp1.getAllAlive()) {
            entity.getEventController().clear();
        }
    }

    @Override
    public void actionChance(Entity self) {
        self.setPosition(1.0);
        log.info(Msg.info(self, "获得一次行动机会"));
    }

    @Override
    public int calcCritical(Entity self, double src) {
        if (!RandUtil.success(self.getCritical()))
            return (int) src;
        log.info(Msg.info(self, "暴击"));
        return (int) (src * self.getCriticalDamage());
    }

    @Override
    public void revive(Entity target, int maxLife) {
        final Position position = getCamp(target).getPositionBySrc(target);
        position.setCurrent(target);
        target.setLife(maxLife);
        log.info(Msg.info(target, "复活，血量", target.getLifeInt()));
        target.getEventController().trigger(new EnterEvent(target));
    }

    @Override
    public int dispelBuff(Entity target, int count) {
        return dispel(target, count, DispellableBuff.class);
    }

    @Override
    public int dispelDebuff(Entity target, int count) {
        return dispel(target, count, DispellableDebuff.class);
    }

    private <T extends IBuff> int dispel(Entity target, final int count, Class<T> clazz) {
        int left = count;
        for (T t : target.getBuffController().getBuffs(clazz)) {
            if (left <= 0)
                break;
            target.getBuffController().remove(t);
            log.info(Msg.info(target, t, "被驱散了"));
            left -= 1;
        }
        return count - left;
    }

    /**
     * 用于进行行动外的动作。
     * 如反击。
     */
    public class SingleAction implements Comparable<SingleAction> {
        private final int prior;
        private final Callback callback;

        SingleAction(int prior, Callback callback) {
            this.prior = prior;
            this.callback = callback;
        }

        Callback getCallback() {
            return callback;
        }

        @Override
        public int compareTo(SingleAction o) {
            return prior - o.prior;
        }

        @Override
        public boolean equals(Object obj) {
            if (super.equals(obj))
                return true;
            if (!(obj instanceof SingleAction))
                return false;
            final SingleAction action = (SingleAction) obj;
            return callback == action.callback || callback.equals(action.callback);
        }
    }
}
