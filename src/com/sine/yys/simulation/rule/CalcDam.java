package com.sine.yys.simulation.rule;

/**
 * 伤害计算
 */
public class CalcDam {
    /**
     * 计算受伤百分比。
     * @param defense 防御值
     * @return
     */
    public static double injuryPct(double defense) {
        return 300.0 / (300.0 + defense);
    }
}
