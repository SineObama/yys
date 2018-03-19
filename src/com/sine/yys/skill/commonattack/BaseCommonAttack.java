package com.sine.yys.skill.commonattack;

import com.sine.yys.event.BeMonoAttackEvent;
import com.sine.yys.event.CommonAttackEvent;
import com.sine.yys.inter.*;
import com.sine.yys.skill.BaseActiveSkill;
import com.sine.yys.skill.model.AttackInfoImpl;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;

/**
 * 普攻通用逻辑。
 * 在普攻结束后触发普攻事件。
 */
public abstract class BaseCommonAttack extends BaseActiveSkill implements CommonAttack {
    @Override
    public final int getFire() {
        return 0;
    }

    /**
     * @return 攻击次数（段数）。
     */
    public int getTimes() {
        return 1;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
    }

    /**
     * 攻击信息。
     * 默认以getCoefficient的系数，没有破防效果。
     *
     * @return 攻击信息。
     */
    @Override
    public AttackInfo getAttack() {
        return new AttackInfoImpl(getCoefficient());
    }

    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    protected void beforeApply(Entity target) {
        // 可能被混乱打自己人
        if (getEnemy().contain(target) && target instanceof ShikigamiEntity) {
            getEnemy().getEventController().trigger(new BeMonoAttackEvent((ShikigamiEntity) target));
        }
    }

    @Override
    public final void afterApply(Entity target) {
        // 触发普攻事件
        getOwn().getEventController().trigger(new CommonAttackEvent(getSelf(), target));
    }

    /**
     * 协战的具体操作。
     * 默认调用doApply。具体技能根据需要重写。
     *
     * @param self   自身。
     * @param target 协战目标。
     */
    @Override
    public void xieZhan(Controller controller, Entity self, Entity target) {
        doApply(target);
    }

    /**
     * 普攻的具体操作。无需触发普攻事件。
     * 默认以getAttack的攻击，对target攻击getTimes次。
     *
     * @param target 攻击目标。
     */
    @Override
    protected void doApply(Entity target) {
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getController().attack(getSelf(), target, getAttack(), getDebuffEffects());
        }
    }
}
