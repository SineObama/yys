package com.sine.yys.skill.mono;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.BaseAttackSkill;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;

/**
 * 单体攻击。
 */
public abstract class BaseMonoAttack extends BaseAttackSkill {
    /**
     * 默认以getAttack的攻击，对target攻击getTimes次。
     */
    @Override
    protected void doApply(Entity target) {
        for (int i = 0; i < getTimes(); i++)
            getController().attack(getSelf(), target, getAttack(), getAttackType());
    }

    /**
     * @return 攻击次数（段数）。
     */
    public int getTimes() {
        return 1;
    }

    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }
}
