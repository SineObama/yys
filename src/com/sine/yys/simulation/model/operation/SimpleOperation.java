package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

public class SimpleOperation implements Operation {
    private final Entity target;
    private final ActiveSkill skill;

    public SimpleOperation(Entity target, ActiveSkill skill) {
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
