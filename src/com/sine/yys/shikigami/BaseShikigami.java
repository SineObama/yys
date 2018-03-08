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
    private final double originAttack;

    BaseShikigami(List<Skill> skills, String name, double originAttack) {
        this.skills = skills;
        this.name = name;
        this.originAttack = originAttack;
    }

    @Override
    public double getOriginAttack() {
        return originAttack;
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
