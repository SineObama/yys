package com.sine.yys.simulation.model.operation;

import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.skill.ActiveSkill;

public class SimpleOperation implements Operation {
    private final Target target;
    private final ActiveSkill skill;

    public SimpleOperation(Target target, ActiveSkill skill) {
        this.target = target;
        this.skill = skill;
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
