package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.skill.targetresolver.EmptyResolver;
import com.sine.yys.simulation.component.skill.targetresolver.TargetResolver;
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
    public void doApply(Entity unused) {
        for (int i = 0; i < getTimes(); i++) {
            for (Entity target : getSelf().getCamp().getOpposite().getAllAlive()) {
                getSelf().attack(target, new AttackInfoImpl(getCoefficient()));
            }
        }
    }
}
