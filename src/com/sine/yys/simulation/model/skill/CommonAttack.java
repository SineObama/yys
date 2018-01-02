package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.battle.ActionContext;
import com.sine.yys.simulation.model.constance.TargetType;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.rule.CalcDam;

/**
 * 普通攻击
 */
public abstract class CommonAttack extends BaseSkill implements ActiveSkill {
    public CommonAttack() {
        super(0);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.Enemy;
    }

    @Override
    public int getFire() {
        return 0;
    }

    public int getTimes() {
        return 1;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    public void apply(ActionContext context) {
        double coefficient = getCoefficient();
        for (int i = 0; i < getTimes(); i++) {
            applyRealDamage(context, context.getTarget(), CalcDam.expect(context.getSelf(), context.getTarget(), coefficient));
        }
    }
}
