package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.Entity;

import java.util.Arrays;
import java.util.List;

public class EnemyCampResolver implements TargetResolver {
    @Override
    public List<? extends Target> resolve(Entity self, Camp own, Camp enemy) {
        return Arrays.asList(enemy);
    }
}
