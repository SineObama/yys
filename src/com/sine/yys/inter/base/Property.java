package com.sine.yys.inter.base;

/**
 * 式神原始的属性。
 * 按顺序包括攻击、生命、防御、速度、暴击、暴击伤害、效果命中、效果抵抗。
 */
public interface Property {
    double getAttack();

    double getLife();

    double getDefense();

    double getSpeed();

    double getCritical();

    double getCriticalDamage();

    double getEffectHit();

    double getEffectDef();
}
