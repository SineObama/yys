package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 受到伤害增减。
 */
public abstract class BeDamageIBuff extends NumIBuff {
    /**
     * @param last     持续回合数。必须为正。
     * @param prefix   名称前缀。
     * @param beDamage 受到伤害增减。
     * @param src      来源式神。
     */
    public BeDamageIBuff(int last, String prefix, double beDamage, Entity src) {
        super(last, prefix + "-受到伤害", beDamage, src);
    }

    @Override
    public final double getBeDamage() {
        return value;
    }
}
