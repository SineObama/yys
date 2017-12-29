package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.constance.TargetType;

/**
 * 简单群攻，可多段。
 */
public abstract class SimpleGroupAttack extends BaseSkill implements ActiveSkill  {
    @Override
    public TargetType getTargetType() {
        return TargetType.EnemyCamp;
    }

    public abstract int getTimes();
}
