package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.ActiveSkill;
import com.sine.yys.simulation.component.TargetResolver;
import com.sine.yys.simulation.component.targetresolver.EmptyResolver;

/**
 * 不需要目标的技能通用逻辑。
 * 虽然只有一个函数。
 */
public abstract class BaseNoTargetSkill extends BaseSkill implements ActiveSkill {
    @Override
    public TargetResolver getTargetResolver() {
        return new EmptyResolver();
    }
}
