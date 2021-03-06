package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.inter.Shikigami;
import com.sine.yys.operation.AutoOperationHandler;
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

    /**
     * 定义式神的技能，获取技能时被调用一次。
     */
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
}
