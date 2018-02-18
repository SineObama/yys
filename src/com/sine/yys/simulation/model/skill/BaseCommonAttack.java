package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.AttackInfo;
import com.sine.yys.simulation.model.AttackInfoImpl;
import com.sine.yys.simulation.model.entity.BaseEntity;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.event.CommonAttackEvent;

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
    public final void afterApply(BaseEntity target) {
        // 触发普攻事件
        getSelf().getCamp().getEventController().trigger(new CommonAttackEvent(getSelf(), target));
    }

    @Override
    public final void xieZhan(Entity target) {
        doXieZhan((BaseEntity) target);
    }

    /**
     * 协战的具体操作。
     * 默认调用doApply。具体技能根据需要重写。
     *
     * @param target 协战目标。
     */
    protected void doXieZhan(BaseEntity target) {
        doApply(target);
    }

    /**
     * 普攻的具体操作。无需触发普攻事件。
     * 默认以getAttack的攻击，对target攻击getTimes次。
     *
     * @param target 攻击目标。
     */
    @Override
    protected void doApply(BaseEntity target) {
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getSelf().attack(target, getAttack());
        }
    }
}
