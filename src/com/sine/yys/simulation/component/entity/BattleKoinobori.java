package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.BaseEntity;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.info.Property;

import java.util.Collections;

/**
 * 战场鲤鱼旗。
 * 还未实现功能。
 */
public class BattleKoinobori extends BaseEntity implements Entity {
    private double damageRatio = 1.0;
    private double cureRatio = 1.0;
    private double damageRatioAddition = 0.2;
    private double cureRatioReduction = 0.2;

    public BattleKoinobori(double speed) {
        super(new Property(0, 0, 0, speed, 0, 0, 0, 0), null, Collections.emptyList(), "战场鲤鱼旗");  // TODO 行动技能：给全体加增伤buff
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
}
