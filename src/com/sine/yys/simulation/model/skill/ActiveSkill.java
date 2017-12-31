package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.constance.TargetType;

/**
 * 主动技能
 */
public interface ActiveSkill extends Skill {
    TargetResolver getTargetResolver();
    TargetType getTargetType();
    double getCoefficient();
}
