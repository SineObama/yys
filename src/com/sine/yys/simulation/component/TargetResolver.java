package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

import java.util.List;

/**
 * 根据技能寻找可能的目标
 */
public interface TargetResolver {
    List<Entity> resolve(ActiveSkill skill);
}
