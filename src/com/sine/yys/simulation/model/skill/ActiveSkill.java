package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 主动技能。
 * 包括所有普攻、群攻等等。
 */
public interface ActiveSkill extends Skill {
    int getFire();

    TargetResolver getTargetResolver();

    /**
     * 直接使用技能。由外部直接调用。
     * 不操作鬼火。
     *
     * @param target
     */
    void apply(Entity target);
}
