package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseCommonIBuff;

/**
 * 战场旗帜效果，随战场鲤鱼旗行动逐渐加强。
 * 造成伤害增加，治疗减少。
 * 不可驱散。
 */
public class BattleFlag extends BaseCommonIBuff {
    private double damage = 0, cure = 0;

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setCure(double cure) {
        this.cure = cure;
    }

    public BattleFlag() {
        super(Integer.MAX_VALUE, "战场效果", null);
    }

    @Override
    public double getCure() {
        return cure;
    }

    @Override
    public double getDamageUp() {
        return damage;
    }

    @Override
    public double getFlagDamage() {
        return damage;
    }
}
