package com.sine.yys.simulation.component.targetresolver;

import com.sine.yys.simulation.component.BaseEntity;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.TargetResolver;

import java.util.List;

/**
 * 可选目标为敌方所有式神（或召唤物）。
 */
public class EnemyEntityResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Entity self0) {
        BaseEntity self = (BaseEntity) self0;
        return self.getCamp().getOpposite().getAllAlive();
    }
}
