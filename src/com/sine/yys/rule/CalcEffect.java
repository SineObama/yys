package com.sine.yys.rule;

/**
 * 计算效果命中。
 */
public class CalcEffect {
    /**
     * 已知抵抗，计算命中概率。相当于100%概率0命中下的命中概率。
     *
     * @param resistance 对方效果抵抗
     * @return
     */
    public static double hitPct(double resistance) {
        return 1.0 / (1.0 + resistance);
    }

    /**
     * 计算实际概率。
     * 实际概率 = 原始概率 * （1 + 效果命中）
     *
     * @param pct 原始概率。
     * @param hit 效果命中。
     * @return 实际概率。
     */
    public static double pct(double pct, double hit) {
        return pct * (1 + hit);
    }
}
