package com.sine.yys.simulation.rule;

import com.sine.yys.simulation.model.Attack;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 伤害计算。
 */
public class CalcDam {
    /**
     * 计算受伤百分比。
     *
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
    public static double expect(Entity self, Entity target, Attack attack, boolean critical) {
        final double criticalCoefficient = critical ? self.getCriticalDamage() : 1.0;
        final double finalDefense = (target.getDefense() - attack.getIgnoreDefense()) * (1.0 - attack.getIgnoreDefensePct());
        return attack.getCoefficient() * injuryPct(finalDefense) * self.getAttack() * criticalCoefficient;
    }
}
