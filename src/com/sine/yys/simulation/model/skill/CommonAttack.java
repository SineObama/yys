package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.EnemyTargetResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.constance.TargetType;

/**
 * 普通攻击
 */
public abstract class CommonAttack  implements ActiveSkill {
    @Override
    public TargetType getTargetType() {
        return TargetType.Enemy;
    }

    @Override
    public int getFire() {
        return 0;
    }

    @Override
    public TargetResolver getTargetResolver() {return new EnemyTargetResolver();}
}
