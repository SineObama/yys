package com.sine.yys.inter;

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
     * 不操作鬼火。
     *
     * @param controller 控制器。
     * @param self       自身。
     * @param target     技能所选目标。
     */
    void apply(Controller controller, Entity self, Entity target);
}
