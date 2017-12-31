package com.sine.yys.simulation.rule;

import com.sine.yys.simulation.model.entity.Entity;

import java.util.Random;

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

    /**
     * 计算某式神攻击目标的期望伤害。
     * 包括暴击的计算（但没有反馈信息）。
     */
    public static int expect(Entity source, Entity target, double coefficient) {
        Random random = new Random();
        double critical = random.nextDouble() < source.getCritical() ? source.getCriticalDamage() : 1.0;
        return (int) (coefficient * injuryPct(target.getDefense()) * source.getAttack() * critical);
    }
}
