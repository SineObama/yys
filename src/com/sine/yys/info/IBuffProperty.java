package com.sine.yys.info;

/**
 * buff中可提供的加成数值，正负数分别表示增减。
 */
public interface IBuffProperty {
    /**
     * 攻击加成百分比。
     */
    double getAtkPct();

    /**
     * 防御加成百分比。
     */
    double getDefPct();

    /**
     * 速度。
     */
    double getSpeed();

    /**
     * 暴击。
     */
    double getCritical();

    /**
     * 暴击伤害。
     */
    double getCriticalDamage();

    /**
     * 效果命中。
     */
    double getEffectHit();

    /**
     * 效果抵抗。
     */
    double getEffectDef();
}
