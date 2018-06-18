package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.inter.Shikigami;
import com.sine.yys.shikigami.operation.AutoOperationHandler;
import com.sine.yys.skill.BaseSkill;

import java.util.Collection;
import java.util.Collections;

/**
 * 式神基类。
 */
public abstract class BaseShikigami implements Shikigami {
    private Collection<BaseSkill> skills;

    BaseShikigami() {
    }

    protected abstract Collection<BaseSkill> initSkill();

    @Override
    public OperationHandler getAI() {
        return new AutoOperationHandler();
    }

    @Override
    public Collection<BaseSkill> getSkills() {
        if (skills == null)
            skills = Collections.unmodifiableCollection(initSkill());
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
