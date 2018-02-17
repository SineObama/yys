package com.sine.yys.simulation.model;

/**
 * 定义一次攻击（一次伤害）中，只与技能本身相关的信息。
 * 包括：系数，忽略防御百分比，忽略防御数值。
 */
public interface AttackInfo {
    double getCoefficient();

    double getIgnoreDefensePct();

    int getIgnoreDefense();
}
