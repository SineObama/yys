package com.sine.yys.base;

import com.sine.yys.inter.Property;

/**
 * 式神属性。
 * 创建式神时已经固定的各项属性。
 * 依次为：攻击，生命，防御，速度，暴击，暴击伤害，效果命中，效果抵抗。
 */
public class PropertyImpl implements Property {
    private final double attack;
    private final double life;
    private final double defense;
    private final double speed;
    private final double critical;
    private final double criticalDamage;
    private final double effectHit;
    private final double effectDef;

    public PropertyImpl(double[] doubles) {
        this.attack = doubles[0];
        this.life = doubles[1];
        this.defense = doubles[2];
        this.speed = doubles[3];
        this.critical = doubles[4];
        this.criticalDamage = doubles[5];
        this.effectHit = doubles[6];
        this.effectDef = doubles[7];
    }

    public PropertyImpl(double attack, double life, double defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef) {
        this.attack = attack;
        this.life = life;
        this.defense = defense;
        this.speed = speed;
        this.critical = critical;
        this.criticalDamage = criticalDamage;
        this.effectHit = effectHit;
        this.effectDef = effectDef;
    }

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public double getLife() {
        return life;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getCritical() {
        return critical;
    }

    @Override
    public double getCriticalDamage() {
        return criticalDamage;
    }

    @Override
    public double getEffectHit() {
        return effectHit;
    }

    @Override
    public double getEffectDef() {
        return effectDef;
    }
}
