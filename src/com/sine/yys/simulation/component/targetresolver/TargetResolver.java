package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

import java.util.List;

/**
 * 根据技能寻找可能的目标
 */
public interface TargetResolver {
    List<Entity> resolve(Entity self, Camp own, Camp enemy);
}
