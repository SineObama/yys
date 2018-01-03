package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.skill.ActiveSkill;

public interface Operation {
    ActiveSkill getSkill();
    Target getTarget();
}
