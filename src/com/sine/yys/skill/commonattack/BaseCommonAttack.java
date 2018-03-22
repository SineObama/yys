package com.sine.yys.skill.commonattack;

import com.sine.yys.inter.CommonAttack;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.BaseAttackSkill;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;

/**
 * 普通攻击。
 * <p>
 * 结束后触发普攻事件。
 */
public abstract class BaseCommonAttack extends BaseAttackSkill implements CommonAttack {
    /**
     * 默认以getAttack的攻击，对target攻击getTimes次。
     */
    @Override
    protected void doApply(Entity target) {
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getController().attack(getSelf(), target, getAttack());
        }
    }

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

    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
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
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getController().counter(getSelf(), target, getAttack());
        }
    }
}
