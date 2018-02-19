package com.sine.yys.simulation.component.entity.targetresolver;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.TargetResolver;

import java.util.Collections;
import java.util.List;

/**
 * 不需要指定目标。
 */
public class EmptyResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Entity self) {
        return Collections.emptyList();
    }
}
