package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.Skill;

public class SimpleOperation implements Operation {
    private final Entity source, target;
    private final ActiveSkill skill;

    public SimpleOperation(Entity source, Entity target, ActiveSkill skill) {
        this.source = source;
        this.target = target;
        this.skill = skill;
    }

    @Override
    public Entity getSource() {
        return source;
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
