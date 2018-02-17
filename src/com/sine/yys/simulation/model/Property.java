package com.sine.yys.simulation.model;

/**
 * 式神属性。
 * 创建式神时已经固定的各项属性。
 * 依次为：攻击，生命，防御，速度，暴击，暴击伤害，效果命中，效果抵抗。
 */
public class Property {
    private final double attack;
    private final double life;
    private final double defense;
    private final double speed;
    private final double critical;
    private final double criticalDamage;
    private final double effectHit;
    private final double effectDef;

    public Property(double attack, double life, double defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef) {
        this.attack = attack;
        this.life = life;
        this.defense = defense;
        this.speed = speed;
        this.critical = critical;
        this.criticalDamage = criticalDamage;
        this.effectHit = effectHit;
        this.effectDef = effectDef;
    }

    public double getAttack() {
        return attack;
    }

    public double getLife() {
        return life;
    }

    public double getDefense() {
        return defense;
    }

    public double getSpeed() {
        return speed;
    }

    public double getCritical() {
        return critical;
    }

    public double getCriticalDamage() {
        return criticalDamage;
    }

    public double getEffectHit() {
        return effectHit;
    }

    public double getEffectDef() {
        return effectDef;
    }
}
