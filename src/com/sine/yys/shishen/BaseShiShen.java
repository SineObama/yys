package com.sine.yys.shishen;

import com.sine.yys.info.Named;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.Skill;
import com.sine.yys.skill.operation.AutoOperationHandler;
import com.sine.yys.skill.operation.OperationHandler;

import java.util.List;

/**
 * 式神。
 * 无状态，只包含特定式神这一信息（名字、技能），不包含属性、御魂、战斗状态。
 */
public abstract class BaseShiShen implements Named, ShiShen {
    final List<BaseSkill> skills;
    final String name;

    public BaseShiShen(List<BaseSkill> skills, String name) {
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
    public List<? extends Skill> getSkills() {
        return skills;
    }
}
