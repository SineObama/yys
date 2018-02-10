package com.sine.yys.simulation.model;

/**
 * 把一次攻击视为一个“气劲”，与暴击、增减伤效果等独立开。
 * 包含信息：系数，忽略防御百分比，忽略防御数值。
 */
public interface Attack {
    double getCoefficient();

    double getIgnoreDefensePct();

    int getIgnoreDefense();
}
