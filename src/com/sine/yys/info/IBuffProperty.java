package com.sine.yys.info;

/**
 * buff中可提供的加成数值，正负数分别表示增减。
 *
 * @see com.sine.yys.rule.buff.Composition
 */
public interface IBuffProperty {
    /**
     * 攻击加成百分比。
     * （1+百分比）*原攻击。
     */
    double getAtkPct();

    /**
     * 防御加成百分比。
     * （1+百分比）*原防御。
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
