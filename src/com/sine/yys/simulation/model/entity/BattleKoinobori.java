package com.sine.yys.simulation.model.entity;

import java.util.Collections;

/**
 * 战场鲤鱼旗。
 */
public class BattleKoinobori extends BaseEntity implements Entity {
    private double speed;
    private double damageRatio = 1.0;
    private double cureRatio = 1.0;
    private double damageRatioAddition = 0.2;
    private double cureRatioReduction = 0.2;

    public BattleKoinobori(double speed) {
        super(0, 0, 0, 0, 0, 0, 0, 0, null);
        this.speed = speed;
    }

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
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public String getName() {
        return "战场鲤鱼旗";
    }
}
