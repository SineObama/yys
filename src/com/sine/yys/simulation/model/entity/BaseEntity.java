package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.AutoOperationHandler;
import com.sine.yys.simulation.component.OperationHandler;
import com.sine.yys.simulation.model.shield.Shield;

/**
 * 式神实体基类。
 * 可以创建固定属性的式神。
 */
public abstract class BaseEntity implements Entity {
    private final int attack;
    private final int maxLife;
    private final int defend;
    private final double speed;
    private final double critical;
    private final double criticalDamage;
    private final double effectHit;
    private final double effectDef;

    private int life;
    private Shield shield = null;

    public BaseEntity(int attack, int maxLife, int defend, double speed, double critical, double criticalDamage, double effectHit, double effectDef) {
        this.attack = attack;
        this.maxLife = maxLife;
        this.defend = defend;
        this.speed = speed;
        this.critical = critical;
        this.criticalDamage = criticalDamage;
        this.effectHit = effectHit;
        this.effectDef = effectDef;

        this.life = maxLife;
    }

    public int getAttack() {
        return attack;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getDefend() {
        return defend;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    @Override
    public OperationHandler getAI() {
        return new AutoOperationHandler();
    }
}
