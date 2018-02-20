package com.sine.yys.simulation.component.shishen;

import com.sine.yys.simulation.component.skill.Skill;
import com.sine.yys.simulation.component.skill.operation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<Skill> getSkills();
}
