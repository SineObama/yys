package com.sine.yys.inter.base;

/**
 * 技能。
 */
public interface Skill extends Named {
    /**
     * @return 剩余冷却时间。
     */
    int getCD();

    /**
     * @return 最大冷却时间。
     */
    int getMAXCD();

    /**
     * 回合开始的回调。
     */
    int beforeAction();

    /**
     * 回合结束的回调。
     */
    int afterAction();
}
