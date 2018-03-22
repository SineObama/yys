package com.sine.yys.skill;

import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.targetresolver.EmptyResolver;

/**
 * 不需要选择目标的主动技能。
 * 虽然只有一个逻辑。
 */
public abstract class BaseNoTargetSkill extends BaseActiveSkill {
    @Override
    public TargetResolver getTargetResolver() {
        return new EmptyResolver();
    }
}
