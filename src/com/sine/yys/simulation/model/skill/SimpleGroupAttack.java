package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.info.AttackInfoImpl;
import com.sine.yys.simulation.model.Entity;
import com.sine.yys.simulation.model.TargetResolver;
import com.sine.yys.simulation.model.entity.BaseEntity;
import com.sine.yys.simulation.model.targetresolver.EmptyResolver;

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
    public void doApply(BaseEntity unused) {
        for (int i = 0; i < getTimes(); i++) {
            for (Entity target : getSelf().getCamp().getOpposite().getAllAlive()) {
                getSelf().attack(target, new AttackInfoImpl(getCoefficient()));
            }
        }
    }
}
