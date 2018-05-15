package com.sine.yys.skill.commonattack;

import com.sine.yys.impl.AttackTypeImpl;
import com.sine.yys.inter.CommonAttack;
import com.sine.yys.inter.Entity;
import com.sine.yys.skill.mono.BaseMonoAttack;

/**
 * 普通攻击。
 */
public abstract class BaseCommonAttack extends BaseMonoAttack implements CommonAttack {
    @Override
    public final int getFire() {
        return 0;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
    }

    /**
     * 协战。
     * <p>
     * 默认调用doApply。具体技能根据需要重写。
     */
    @Override
    public void xieZhan(Entity target) {
        doApply(target);
    }

    @Override
    public final void counter(Entity target) {
        doBeforeAction();
        doCounter(target);
        doAfterAction();
    }

    protected void doCounter(Entity target) {
        for (int i = 0; i < getTimes(); i++)
            getController().attack(getSelf(), target, getAttack(), AttackTypeImpl.createCounter());
    }
}
