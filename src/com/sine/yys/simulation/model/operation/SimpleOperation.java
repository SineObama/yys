package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

public class SimpleOperation implements Operation {
    private final Entity source;
    private final Target target;
    private final ActiveSkill skill;

    public SimpleOperation(Entity source, Target target, ActiveSkill skill) {
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
    public Target getTarget() {
        return target;
    }
}
