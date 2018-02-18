package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.ActiveSkill;
import com.sine.yys.simulation.model.Entity;

/**
 * 描述一次行动：使用的技能和目标。
 * 如果技能不需要一个目标（定义上已确定逻辑，不需要明确的目标，比如对敌方全体、己方全体等），则目标为null。
 */
public interface Operation {
    ActiveSkill getSkill();

    Entity getTarget();
}
