package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.List;

/**
 * 需要选择目标的技能，必须可获得这个类，以获得可能的目标列表。
 */
public interface TargetResolver {
    List<? extends Target> resolve(Entity self, Camp own, Camp enemy);
}
