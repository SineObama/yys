package com.sine.yys.skill.targetresolver;

import com.sine.yys.inter.Camp;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.TargetResolver;

import java.util.Collections;
import java.util.List;

/**
 * 不需要指定目标。
 */
public class EmptyResolver implements TargetResolver {
    @Override
    public List<? extends Entity> resolve(Camp own, Entity self) {
        return Collections.emptyList();
    }
}
