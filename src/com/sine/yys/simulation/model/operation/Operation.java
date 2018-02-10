package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.skill.ActiveSkill;

/**
 * 描述一次行动：使用的技能和目标。
 */
public interface Operation {
    ActiveSkill getSkill();

    Target getTarget();
}
