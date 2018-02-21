package com.sine.yys.simulation.model.battle;

/**
 * 可禁用的。
 * 用于描述御魂和被动，因为存在封印效果。
 */
public interface Sealable {
    boolean sealed();
}
