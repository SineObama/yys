package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.shishen.skill.Skill;
import com.sine.yys.simulation.component.shishen.skill.operation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<? extends Skill> getSkills();
}
