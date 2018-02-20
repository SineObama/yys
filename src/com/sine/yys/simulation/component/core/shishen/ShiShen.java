package com.sine.yys.simulation.component.core.shishen;

import com.sine.yys.simulation.component.core.skill.Skill;
import com.sine.yys.simulation.component.core.skill.operation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<Skill> getSkills();
}
