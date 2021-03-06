package com.sine.yys.operation;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Operation;

public class OperationImpl implements Operation {
    private final Entity target;
    private final ActiveSkill skill;

    public OperationImpl(Entity target, ActiveSkill skill) {
        this.target = target;
        this.skill = skill;
    }

    @Override
    public ActiveSkill getSkill() {
        return skill;
    }

    @Override
    public Entity getTarget() {
        return target;
    }
}
