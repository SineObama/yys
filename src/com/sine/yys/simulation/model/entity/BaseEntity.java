package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.operationhandler.AutoOperationHandler;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * 式神实体基类。
 * 可以创建固定属性的式神。
 */
public abstract class BaseEntity implements Entity {
    private final int attack;
    private final int maxLife;
    private final int defense;
    private final double speed;
    private final double critical;
    private final double criticalDamage;
    private final double effectHit;
    private final double effectDef;

    private int life;
    private Shield shield = null;

    private List<Skill> skills = null;

    public BaseEntity(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, List<Skill> skills) {
        this.attack = attack;
        this.maxLife = maxLife;
        this.defense = defense;
        this.speed = speed;
        this.critical = critical;
        this.criticalDamage = criticalDamage;
        this.effectHit = effectHit;
        this.effectDef = effectDef;

        this.life = maxLife;
        this.skills = skills;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public int getDefense() {
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

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public Shield getShield() {
        return shield;
    }

    @Override
    public void setShield(Shield shield) {
        this.shield = shield;
    }

    @Override
    public OperationHandler getAI() {
        return new AutoOperationHandler();
    }

    @Override
    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public List<ActiveSkill> getActiveSkills() {
        List<ActiveSkill> activeSkills = new ArrayList<>();
        for (Skill skill : skills) {
            if (skill instanceof ActiveSkill) {
                activeSkills.add((ActiveSkill) skill);
            }
        }
        return activeSkills;
    }
}
