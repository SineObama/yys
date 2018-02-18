package com.sine.yys.simulation.component;

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
