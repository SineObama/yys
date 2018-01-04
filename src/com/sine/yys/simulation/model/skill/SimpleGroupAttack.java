package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.targetresolver.EnemyCampResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 简单群攻，可多段。
 */
public abstract class SimpleGroupAttack extends BaseSkill implements ActiveSkill {
    public int getTimes() {
        return 1;
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyCampResolver();
    }

    @Override
    public void apply(Controller controller) {
        controller.useFire(getFire());
        for (int i = 0; i < getTimes(); i++) {
            for (Entity target : controller.getEnemy().getAllAlive()) {
                controller.setTarget(target);
                controller.damage(target, getCoefficient());
            }
        }
    }
}
