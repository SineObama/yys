package com.sine.yys.simulation.component.skill.operation;

import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.component.skill.ActiveSkill;

public class Operation {
    private final Entity target;
    private final ActiveSkill skill;

    public Operation(Entity target, ActiveSkill skill) {
        this.target = target;
        this.skill = skill;
    }

    public ActiveSkill getSkill() {
        return skill;
    }

    public Entity getTarget() {
        return target;
    }
}
