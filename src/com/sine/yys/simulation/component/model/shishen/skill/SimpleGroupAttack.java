package com.sine.yys.simulation.component.model.shishen.skill;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;
import com.sine.yys.simulation.component.model.shishen.skill.targetresolver.EmptyResolver;
import com.sine.yys.simulation.component.model.shishen.skill.targetresolver.TargetResolver;
import com.sine.yys.simulation.info.AttackInfoImpl;

/**
 * 简单群攻，可多段。
 */
public abstract class SimpleGroupAttack extends BaseActiveSkill implements AttackSkill {
    @Override
    public int getFire() {
        return 3;
    }

    public int getTimes() {
        return 1;
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EmptyResolver();
    }

    @Override
    public void doApply(Controller controller, Entity self, Entity unused) {
        for (int i = 0; i < getTimes(); i++) {
            for (Entity target : controller.getCamp(self).getOpposite().getAllAlive()) {
                controller.attack(target, new AttackInfoImpl(getCoefficient()));
            }
        }
    }
}
