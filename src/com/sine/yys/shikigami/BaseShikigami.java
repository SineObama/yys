package com.sine.yys.shikigami;

import com.sine.yys.info.Named;
import com.sine.yys.skill.Skill;
import com.sine.yys.skill.operation.AutoOperationHandler;
import com.sine.yys.skill.operation.OperationHandler;

import java.util.List;

/**
 * 式神基类。
 */
public abstract class BaseShikigami implements Named, Shikigami {
    final List<Skill> skills;
    final String name;

    public BaseShikigami(List<Skill> skills, String name) {
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
