package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 受到伤害增减。
 */
public abstract class BeDamageIBuff extends NumIBuff {
    public BeDamageIBuff(int last, String prefix, double beDamage, Entity src) {
        super(last, prefix + "-受到伤害", beDamage, src);
    }

    @Override
    public final double getBeDamage() {
        return value;
    }
}
