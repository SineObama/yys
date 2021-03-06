package com.sine.yys.inter.base;

/**
 * {@linkplain com.sine.yys.inter.IBuff buff}可提供的加成数值，正负数分别表示增减。
 * <p>
 * 叠加规则见{@linkplain com.sine.yys.rule.buff.Composition}
 */
public interface IBuffProperty {
    /**
     * 受到伤害增减。
     * (1+x)*(1+x)....-1。
     */
    double getBeDamage();

    /**
     * 治疗效果。
     * 一般减疗都不能叠加，只有和旗帜buff叠加。
     * （1+百分比）*原治疗量。
     */
    double getCure();

    /**
     * 伤害加成百分比。
     * （1+百分比）*原伤害。
     */
    double getDamageUp();

    /**
     * 旗帜buff的伤害加成百分比。
     * （1+百分比）*原伤害。
     * 只用于特定伤害，目前只有针女。
     */
    double getFlagDamage();

    /**
     * 攻击加成百分比。
     * （1+百分比）*原始攻击。
     */
    double getAtkPct();

    /**
     * 防御加成百分比。
     * （1+百分比）*防御。
     */
    double getDefPct();

    /**
     * 速度。
     * 直接相加。
     */
    double getSpeed();

    /**
     * 暴击。
     * 直接相加。
     */
    double getCritical();

    /**
     * 暴击伤害。
     * 直接相加。
     */
    double getCriticalDamage();

    /**
     * 效果命中。
     * 直接相加。
     */
    double getEffectHit();

    /**
     * 效果抵抗。
     * 直接相加。
     */
    double getEffectDef();
}
