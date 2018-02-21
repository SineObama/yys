package com.sine.yys.simulation.model.battle;

/**
 * 只用作统一输出。
 * 作为主语，可能是阵营或者式神。
 */
public interface Target extends Named {
    /**
     * 带阵营的全名。
     */
    String getFullName();
}
