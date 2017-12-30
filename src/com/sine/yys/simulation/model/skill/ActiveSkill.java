package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.constance.TargetType;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 主动技能
 */
public interface ActiveSkill extends Skill {
    TargetResolver getTargetResolver();
    TargetType getTargetType();
    int getFire();
    double getCoefficient();
}
