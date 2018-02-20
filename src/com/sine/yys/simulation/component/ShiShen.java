package com.sine.yys.simulation.component;

import com.sine.yys.simulation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<Skill> getSkills();
}
