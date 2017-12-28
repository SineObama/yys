package com.sine.model;

public abstract class BaseEntity {
    private final int attack;
    private final int maxLife;
    private final int defend;
    private final int speed;
    private final float crit;
    private final float critDamage;
    private final float effectHit;
    private final float effectDef;

    private int life;
    private int shield;

    public BaseEntity(int attack, int maxLife, int defend, int speed, float crit, float critDamage, float effectHit, float effectDef) {
        this.attack = attack;
        this.maxLife = maxLife;
        this.defend = defend;
        this.speed = speed;
        this.crit = crit;
        this.critDamage = critDamage;
        this.effectHit = effectHit;
        this.effectDef = effectDef;

        this.life = maxLife;
        this.shield = 0;
    }
}
