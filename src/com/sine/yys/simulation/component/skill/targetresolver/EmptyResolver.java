package com.sine.yys.simulation.component.skill.targetresolver;

import com.sine.yys.simulation.component.inter.Controller;
import com.sine.yys.simulation.component.inter.Entity;

import java.util.Collections;
import java.util.List;

/**
 * 不需要指定目标。
 */
public class EmptyResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Controller controller, Entity self) {
        return Collections.emptyList();
    }
}
