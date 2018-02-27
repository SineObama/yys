package com.sine.yys.skill;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.targetresolver.EmptyResolver;

/**
 * 不需要选择目标的主动技能通用逻辑。
 * 虽然只有一个函数。
 */
public abstract class BaseNoTargetSkill extends BaseActiveSkill implements ActiveSkill {
    @Override
    public TargetResolver getTargetResolver() {
        return new EmptyResolver();
    }
}
