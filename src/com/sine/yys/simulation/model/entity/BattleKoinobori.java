package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.component.OperationHandler;
import com.sine.yys.simulation.model.shield.Shield;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * 战场鲤鱼旗。
 */
public class BattleKoinobori implements Entity {
    private double speed;
    private double damageRatio = 1.0;
    private double cureRatio = 1.0;
    private double damageRatioAddition = 0.2;
    private double cureRatioReduction = 0.2;

    public double getDamageRatio() {
        return damageRatio;
    }

    public void setDamageRatio(double damageRatio) {
        this.damageRatio = damageRatio;
    }

    public double getCureRatio() {
        return cureRatio;
    }

    public void setCureRatio(double cureRatio) {
        this.cureRatio = cureRatio;
    }

    public double getDamageRatioAddition() {
        return damageRatioAddition;
    }

    public void setDamageRatioAddition(double damageRatioAddition) {
        this.damageRatioAddition = damageRatioAddition;
    }

    public double getCureRatioReduction() {
        return cureRatioReduction;
    }

    public void setCureRatioReduction(double cureRatioReduction) {
        this.cureRatioReduction = cureRatioReduction;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public OperationHandler getAI() {
        return null;
    }

    @Override
    public List<Skill> getSkills() {
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return "战场鲤鱼旗";
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public int getMaxLife() {
        return 0;
    }

    @Override
    public int getDefend() {
        return 0;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getCritical() {
        return 0;
    }

    @Override
    public double getCriticalDamage() {
        return 0;
    }

    @Override
    public double getEffectHit() {
        return 0;
    }

    @Override
    public double getEffectDef() {
        return 0;
    }

    @Override
    public int getLife() {
        return 0;
    }

    @Override
    public void setLife(int life) {

    }

    @Override
    public Shield getShield() {
        return null;
    }

    @Override
    public void setShield(Shield shield) {

    }
}
