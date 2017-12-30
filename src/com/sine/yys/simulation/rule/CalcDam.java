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

    public static double attack(Entity source, Entity target) {
        Random random = new Random();
        int life = target.getLife();
        double critical = random.nextDouble() < source.getCritical() ? source.getCriticalDamage() : 1.0;
        return injuryPct(target.getDefense()) * source.getAttack() * critical;
    }
}
