package com.sine.yys.simulation.component.model.shishen;

import com.sine.yys.simulation.component.model.skill.Skill;
import com.sine.yys.simulation.component.model.skill.operation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<? extends Skill> getSkills();
}
