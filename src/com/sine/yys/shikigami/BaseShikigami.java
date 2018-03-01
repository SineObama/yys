package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.inter.Shikigami;
import com.sine.yys.inter.Skill;
import com.sine.yys.skill.operation.AutoOperationHandler;

import java.util.List;

/**
 * 式神基类。
 */
public abstract class BaseShikigami implements Shikigami {
    final List<Skill> skills;
    private final String name;

    BaseShikigami(List<Skill> skills, String name) {
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
