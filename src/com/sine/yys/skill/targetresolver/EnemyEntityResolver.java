package com.sine.yys.skill.targetresolver;

import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TargetResolver;

import java.util.List;

/**
 * 可选目标为敌方所有式神（或召唤物）。
 */
public class EnemyEntityResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Camp own, Entity self) {
        return own.getOpposite().getAllAlive();
    }
}
