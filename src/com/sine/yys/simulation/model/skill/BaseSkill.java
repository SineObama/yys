package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.model.entity.Entity;

public abstract class BaseSkill implements ActiveSkill {
    @Override
    public final void doApply(Entity self, Camp own, Camp enemy, Entity target) {

    }
}
