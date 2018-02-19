package com.sine.yys.simulation.component.entity.targetresolver;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.TargetResolver;

import java.util.List;

/**
 * 可选目标为敌方所有式神（或召唤物）。
 */
public class EnemyEntityResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Entity self) {
        return self.getCamp().getOpposite().getAllAlive();
    }
}
