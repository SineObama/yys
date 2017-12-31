package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.EnemyTargetResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.constance.TargetType;

/**
 * 简单群攻，可多段。
 */
public abstract class SimpleGroupAttack implements ActiveSkill {
    @Override
    public TargetType getTargetType() {
        return TargetType.EnemyCamp;
    }

    public abstract int getTimes();

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyTargetResolver();
    }
}
