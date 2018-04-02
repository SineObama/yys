package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.inter.Shikigami;
import com.sine.yys.shikigami.operation.AutoOperationHandler;
import com.sine.yys.skill.BaseSkill;

import java.util.Collection;

/**
 * 式神基类。
 */
public abstract class BaseShikigami implements Shikigami {
    private final Collection<BaseSkill> skills;
    private final String name;
    private final double originAttack;

    BaseShikigami(Collection<BaseSkill> skills, String name, double originAttack) {
        this.skills = skills;
        this.name = name;
        this.originAttack = originAttack;
    }

    BaseShikigami(InitSkill initSkill, String name, double originAttack) {
        this.skills = initSkill.init();
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
    public Collection<BaseSkill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return getName();
    }

    @FunctionalInterface
    interface InitSkill {
        Collection<BaseSkill> init();
    }
}
