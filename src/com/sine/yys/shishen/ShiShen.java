package com.sine.yys.shishen;

import com.sine.yys.skill.Skill;
import com.sine.yys.skill.operation.OperationHandler;

import java.util.List;

public interface ShiShen {
    String getName();

    OperationHandler getAI();

    List<? extends Skill> getSkills();
}
