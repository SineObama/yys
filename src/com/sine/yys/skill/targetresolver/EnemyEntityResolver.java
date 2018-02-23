package com.sine.yys.skill.targetresolver;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

import java.util.List;

/**
 * 可选目标为敌方所有式神（或召唤物）。
 */
public class EnemyEntityResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Controller controller, Entity self) {
        return controller.getCamp(self).getOpposite().getAllAlive();
    }
}
