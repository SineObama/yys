package com.sine.yys.simulation.rule;

import com.sine.yys.simulation.model.constance.HitResult;

import java.util.Random;

/**
 * 计算效果命中
 */
public class CalcEffect {
    /**
     * 计算命中概率。相当于100%概率0命中下的命中概率。
     *
     * @param resistance 对方效果抵抗
     * @return
     */
    static double hitPct(double resistance) {
        return 1.0 / (1.0 + resistance);
    }

    /**
     * 计算技能效果命中结果
     *
     * @param pro        技能本身命中率
     * @param hit        效果命中
     * @param resistance 对方效果抵抗
     * @return
     */
    public static HitResult calc(double pro, double hit, double resistance) {
        double proHit0 = pro * (1 + hit);
        Random random = new Random();
        if (random.nextDouble() > proHit0)
            return HitResult.Miss;
        if (proHit0 < 1)
            proHit0 = 1;
        return random.nextDouble() < proHit0 * hitPct(resistance) ? HitResult.Hit : HitResult.Resist;
    }
}
