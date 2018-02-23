package com.sine.yys.skill.operation;

import com.sine.yys.inter.Entity;
import com.sine.yys.skill.ActiveSkill;

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
