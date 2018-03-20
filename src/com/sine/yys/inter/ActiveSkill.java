package com.sine.yys.inter;

import com.sine.yys.inter.base.Skill;

/**
 * 主动技能。
 * 包括所有普攻、群攻等等。
 * 是回合中需要选择使用的技能。
 */
public interface ActiveSkill extends Skill {
    /**
     * @return 消耗鬼火数。
     */
    int getFire();

    TargetResolver getTargetResolver();

    /**
     * 直接使用技能。由外部直接调用。
     *
     * @param target 技能所选目标。
     */
    void apply(Entity target);
}
