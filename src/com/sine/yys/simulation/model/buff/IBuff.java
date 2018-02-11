package com.sine.yys.simulation.model.buff;

/**
 * 效果（式神头上的），分为增益和减益两大类。
 * 基本的有加攻、防御、抵抗等。、
 * 也包括护盾。
 */
public interface IBuff {
    /**
     * 效果名称。
     */
    String getName();

    /**
     * 效果持续回合数。
     */
    int getLast();
}
