package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.List;

public class EnemyEntityResolver implements TargetResolver {
    @Override
    public List<? extends Target> resolve(Entity self, Camp own, Camp enemy) {
        return enemy.getAllAlive();
    }
}
