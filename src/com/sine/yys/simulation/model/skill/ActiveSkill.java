package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.model.constance.TargetType;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 主动技能
 */
public interface ActiveSkill extends Skill {
    TargetType getTargetType();
    int getFire();
    double getCoefficient();
    void doApply(Entity self, Camp own, Camp enemy, Entity target);
    void doOperation(Entity self);
}
