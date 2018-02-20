package com.sine.yys.simulation.component.core.skill.targetresolver;

import com.sine.yys.simulation.component.inter.Entity;

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
