package com.sine.yys.simulation.component;

import com.sine.yys.buff.buff.DispellableBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.buff.shield.Shield;
import com.sine.yys.event.*;
import com.sine.yys.inter.*;
import com.sine.yys.rule.CalcDam;
import com.sine.yys.rule.CalcEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

/**
 * 主要战场逻辑。
 */
public class ControllerImpl implements Controller {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final BaseCamp camp0;
    private final BaseCamp camp1;
    private final Queue<SingleAction> actions = new PriorityQueue<>();

    ControllerImpl(BaseCamp camp0, BaseCamp camp1) {
        this.camp0 = camp0;
        this.camp1 = camp1;
    }

    @Override
    public void addAction(int prior, CallBack callBack) {
        if (!actions.contains(new SingleAction(prior, callBack)))
            actions.add(new SingleAction(prior, callBack));
    }

    CallBack getFirstAction() {
        final SingleAction poll = actions.poll();
        if (poll == null)
            return null;
        return poll.getCallback();
    }

    @Override
    public Camp getCamp0() {
        return camp0;
    }

    @Override
    public Camp getCamp1() {
        return camp1;
    }

    @Override
    public Camp getCamp(Entity entity) {
        return ((EntityImpl) entity).getCamp();
    }

    /**
     * 伤害逻辑：
     * 1. 由攻击、伤害系数、对方防御（忽略防御）计算。
     * 2. 根据双方buff进行增减。
     * 3. 破盾，施加剩余伤害，添加御魂效果。
     */
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

        self.eventController.trigger(new AttackEvent(self, target));

        target.getCamp().getEventController().trigger(new BeAttackEvent());
        target.getEventController().trigger(new BeAttackEvent());

        // 1.
        final boolean critical = RandUtil.success(self.getCritical());
        if (critical)
            log.info(Msg.info(self, "暴击"));
        double damage = CalcDam.expect(self, target, attackInfo, critical);
        damage *= attackInfo.randomFloat();

        // 2.
        damage *= self.getDamageCoefficient();

        // 3.
        applyDamage(self, target, damage, critical, false);
    }

    @Override
    public void realDamage(Entity self0, Entity target0, double damage) {
        EntityImpl self = (EntityImpl) self0;
        EntityImpl target = (EntityImpl) target0;
        if (target.isDead())
            return;

        self.eventController.trigger(new AttackEvent(self, target));

        // 1.
        damage *= self.getFlagDamageCoefficient();

        // 2. 3.
        applyDamage(self, target, damage, false, true);
    }

    /**
     * 普通伤害的施加（包括破盾）。
     * 破盾后计算御魂效果，进行伤害分摊。
     * XXXX 伤害的附加效果的触发位置？
     */
    private void applyDamage(EntityImpl self, EntityImpl target, double damage, boolean critical, boolean zhenNv) {
        // 破盾
        int remain = breakShield(target, (int) damage);

        if (remain != 0) {
            damage = remain;

            PreDamageEvent event = new PreDamageEvent(self, target);
            self.eventController.trigger(event);
            damage *= event.getCoefficient();

            // 处理薙魂。未来考虑金鱼、小松丸躲避。
            final DamageShareEvent damageShareEvent = new DamageShareEvent(target, damage);
            target.eventController.trigger(damageShareEvent);
            damage = damageShareEvent.getLeft();

            // 附加效果
            self.eventController.trigger(new DamageEvent(self, target));
            log.info(Msg.damage(self, target, (int) damage, critical));
            doDamage(target, (int) damage);
            if (target.getLifeInt() == 0)
                log.info(Msg.vector(self, "击杀", target, ""));

            if (critical) {
                target.eventController.trigger(new BeCriticalEvent(target, self));
                self.eventController.trigger(new CriticalEvent(self, target));
            }
        } else {
            log.info(Msg.noDamage(self, target));
        }
    }

    public void directDamage(Entity self0, int damage) {
        if (self0.isDead())  // XXXX 每次都判断死亡是不是太难看
            return;
        EntityImpl self = (EntityImpl) self0;
        damage = breakShield(self, damage);
        log.info(Msg.info(self, "受到伤害", damage));
        doDamage(self, damage);
    }

    /**
     * 治疗。（不会计算暴击）
     */
    private int cure(Entity target0, double src) {
        EntityImpl target = (EntityImpl) target0;
        final double coefficient = target.getCureCoefficient();
        int count;
        log.info(Msg.info(target, "治疗效果", coefficient));
        count = (int) (src * coefficient);
        if (count <= 0)
            count = 1;
        log.info(Msg.info(target, "受到治疗回复", count));
        target.addLife(count);
        final BeCureEvent beCureEvent = new BeCureEvent(count);
        target.getEventController().trigger(beCureEvent);
        target.getCamp().getEventController().trigger(beCureEvent);
        return count;
    }

    @Override
    public int cure(Entity self, Entity target, double src) {
        return cure(target, calcCritical(self, src));
    }

    /**
     * 直接减少目标生命，触发{@link BeDamageEvent}事件。
     */
    private void doDamage(EntityImpl target, int damage) {
        final double src = target.getLife();
        final int srcLife = target.getLifeInt();
        final int life = target.reduceLife(damage);
        final double dst = target.getLife();
        final BeDamageEvent event = new BeDamageEvent(src, dst, srcLife - life);
        target.getEventController().trigger(event);
        target.getCamp().getEventController().trigger(event);
        if (life == 0) {
            // 添加匣中少女逻辑，回复状态则退出
            target.getBuffController().clear();// XXX 匣中少女回复状态会不会保留buff？
            target.getCamp().getPosition(target).setCurrent(null);
            log.info(Msg.info(target, "死亡"));
            // 包括阎魔放小鬼
            target.getEventController().trigger(new DieEvent(target));
            target.getCamp().getEventController().trigger(new DieEvent(target));
            // 添加击杀事件
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
    public void applyDebuff(Entity self, Entity target0, DebuffEffect effect) {
        EntityImpl target = (EntityImpl) target0;
        final double pct;
        final boolean involveHitAndDef = effect.involveHitAndDef();
        if (involveHitAndDef)
            pct = CalcEffect.pct(effect.getPct(), self.getEffectHit());
        else
            pct = effect.getPct();
        if (RandUtil.success(pct)) {
            log.info(Msg.trigger(self, effect));
            Debuff debuff = effect.getDebuff(self);
            if (!involveHitAndDef || RandUtil.success(CalcEffect.hitPct(target.getEffectDef()))) {
                boolean effective = true;
                if (debuff instanceof ControlBuff) {
                    BeforeControlEvent event = new BeforeControlEvent((ControlBuff) debuff);
                    target.getEventController().trigger(event);
                    target.getCamp().getEventController().trigger(event);
                    effective = !event.isNotEffective();
                }
                if (effective) {
                    log.info(Msg.info(target, "获得负面效果", debuff.getName()));
                    target.getBuffController().add(debuff);
                }
            } else {
                log.info(Msg.info(target, "抵抗了负面效果", debuff.getName()));
            }
        }
    }

    @Override
    public void xieZhan(Entity self0, Entity target) {
        EntityImpl self = (EntityImpl) self0;
        if (!self.getCamp().getOpposite().contain(target)) {  // 目标不在对方阵营中。可能已被（队友普攻）击杀，或者目标为自己人（队友混乱攻击）
            log.info(Msg.vector(target, "不在", self, "敌方阵营中，随机协战"));
            target = self.getCamp().getOpposite().randomTarget();
        }
        if (target != null)
            self.getCommonAttack().xieZhan(this, self, target);
    }

    /**
     * 一次“动作”结束后的逻辑。
     * <p>
     * 之前用于实现群体/多段攻击不重复计算，触发一次后会关闭BeAttackEvent事件。
     * 技能调用此函数以重置状态。
     */
    public void afterMovement() {
        camp0.getEventController().trigger(new AfterMovementEvent());
        camp1.getEventController().trigger(new AfterMovementEvent());

        // 重置事件状态
        camp0.eventController.clear();
        camp1.eventController.clear();
        for (EntityImpl entity : camp0.getAllAlive()) {
            entity.eventController.clear();
        }
        for (EntityImpl entity : camp1.getAllAlive()) {
            entity.eventController.clear();
        }
    }

    @Override
    public void actionChance(Entity self0) {
        EntityImpl self = (EntityImpl) self0;
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
    public void revive(Entity target0, int maxLife) {
        EntityImpl target = (EntityImpl) target0;
        final Position position = target.getCamp().getPositionBySrc(target);
        position.setCurrent(target);
        target.setLife(maxLife);
        log.info(Msg.info(target, "复活，血量", target.getLifeInt()));
        final EnterEvent enterEvent = new EnterEvent(target);
        target.eventController.trigger(enterEvent);
        target.getCamp().getEventController().trigger(enterEvent);
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
        private final CallBack callback;

        SingleAction(int prior, CallBack callback) {
            this.prior = prior;
            this.callback = callback;
        }

        CallBack getCallback() {
            return callback;
        }

        @Override
        public int compareTo(SingleAction o) {
            return prior - o.prior;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj) || obj instanceof SingleAction && callback.equals(((SingleAction) obj).callback);
        }
    }
}
