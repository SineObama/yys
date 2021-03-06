package com.sine.yys.rule;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.inter.base.Property;

/**
 * 伤害计算。
 */
public class CalcDam {
    /**
     * @param defense 防御值。
     * @return 受伤百分比，即受到伤害时要乘以的系数。
     */
    public static double injuryPct(double defense) {
        return 300.0 / (300.0 + defense);
    }

    /**
     * 计算某式神攻击目标的期望伤害。
     */
    public static double expect(Property self, Property target, AttackInfo attackInfo, boolean critical) {
        final double criticalCoefficient = critical ? self.getCriticalDamage() : 1.0;
        double defense = target.getDefense() - attackInfo.getIgnoreDefense();
        if (defense < 0)
            defense = 0.0;
        final double finalDefense = defense * (1.0 - attackInfo.getIgnoreDefensePct());
        return attackInfo.getCoefficient() * injuryPct(finalDefense) * self.getAttack() * criticalCoefficient;
    }
}
