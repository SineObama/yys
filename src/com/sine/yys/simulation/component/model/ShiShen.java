package com.sine.yys.simulation.component.model;

import com.sine.yys.simulation.component.model.shishen.skill.Skill;
import com.sine.yys.simulation.component.model.shishen.skill.operation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<? extends Skill> getSkills();
}
