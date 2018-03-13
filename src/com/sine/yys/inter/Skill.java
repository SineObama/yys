package com.sine.yys.inter;

/**
 * 技能。
 * 包含冷却时间（CD）的逻辑。
 */
public interface Skill extends Named {
    void init(Controller controller, Entity self);

    int getCD();

    int getMAXCD();

    /**
     * 每回合开始调用。
     */
    int beforeAction();

    /**
     * 每回合结束调用。
     */
    int afterAction();
}
