package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

public interface Operation {
    Entity getSource();
    ActiveSkill getSkill();
    Entity getTarget();
}
