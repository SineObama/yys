package com.sine.yys.inter;

/**
 * 主动技能。
 * <p>
 * 回合中需要选择使用的技能。
 * <p>
 * 包括所有普攻、群攻等等。
 */
public interface ActiveSkill extends Skill {
    /**
     * @return 消耗鬼火数。
     */
    int getFire();

    TargetResolver getTargetResolver();

    /**
     * 使用技能。
     *
     * @param target 技能所选目标。对于不需要目标的技能可为null，值将被忽略。
     */
    void apply(Entity target);
}
