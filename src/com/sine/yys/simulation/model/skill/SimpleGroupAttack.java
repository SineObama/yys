package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.EnemyCampResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.battle.ActionContext;
import com.sine.yys.simulation.model.constance.TargetType;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.rule.CalcDam;

/**
 * 简单群攻，可多段。
 */
public abstract class SimpleGroupAttack extends BaseSkill implements ActiveSkill {
    public SimpleGroupAttack() {
        super(0);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.EnemyCamp;
    }

    public int getTimes() {
        return 1;
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyCampResolver();
    }

    @Override
    public void apply(ActionContext context) {
        double coefficient = getCoefficient();
        for (int i = 0; i < getTimes(); i++) {
            for (Entity target : context.getEnemy().getAllAlive()) {
                applyRealDamage(context, target, CalcDam.expect(context.getSelf(), target, coefficient));
            }
        }
    }
}
