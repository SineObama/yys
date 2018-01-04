package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;

/**
 * 普通攻击
 */
public abstract class CommonAttack extends BaseSkill implements ActiveSkill {
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
    public void apply(Controller controller) {
        for (int i = 0; i < getTimes(); i++) {
            controller.damage(getCoefficient());
        }
    }
}
