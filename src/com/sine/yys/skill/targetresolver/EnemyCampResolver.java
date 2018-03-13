package com.sine.yys.skill.targetresolver;

import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TargetResolver;

import java.util.Collections;
import java.util.List;

/**
 * 不需要指定目标。
 * 但需要敌方阵营有存活。
 */
public class EnemyCampResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Camp own, Entity self) {
        return own.getOpposite().getAllAlive().size() > 0 ? Collections.emptyList() : null;
    }
}
