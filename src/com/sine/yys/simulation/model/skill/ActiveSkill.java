package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.ContextAndRunner;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;

/**
 * 主动技能
 */
public interface ActiveSkill extends Skill {
    int getFire();

    TargetResolver getTargetResolver();

    double getCoefficient();

    void apply(ContextAndRunner context);
}
