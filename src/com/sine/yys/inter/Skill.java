package com.sine.yys.inter;

/**
 * 技能。
 * 只有逻辑，状态保存在{@link Entity}中。
 * 获取CD等，提供step函数。
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
