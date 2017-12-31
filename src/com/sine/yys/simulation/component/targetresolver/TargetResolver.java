package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.List;

/**
 * 根据技能寻找可能的目标
 */
public interface TargetResolver {
    List<? extends Target> resolve(Entity self, Camp own, Camp enemy);
}
