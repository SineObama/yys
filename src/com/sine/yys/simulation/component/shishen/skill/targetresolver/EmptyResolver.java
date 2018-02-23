package com.sine.yys.simulation.component.shishen.skill.targetresolver;

import com.sine.yys.simulation.component.Entity;

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
