package com.sine.yys.simulation.component.shishen;

import com.sine.yys.simulation.component.skill.Skill;
import com.sine.yys.simulation.component.skill.operation.AutoOperationHandler;
import com.sine.yys.simulation.component.skill.operation.OperationHandler;
import com.sine.yys.simulation.info.Named;

import java.util.List;

/**
 * 式神。
 * 无状态，只包含特定式神这一信息（名字、技能），不包含属性、御魂、战斗状态。
 */
public abstract class BaseShiShen implements Named, ShiShen {
    final List<Skill> skills;
    final String name;

    public BaseShiShen(List<Skill> skills, String name) {
        this.skills = skills;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OperationHandler getAI() {
        return new AutoOperationHandler();
    }

    @Override
    public List<Skill> getSkills() {
        return skills;
    }
}
