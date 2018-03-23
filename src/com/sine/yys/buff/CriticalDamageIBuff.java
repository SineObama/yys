package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 暴伤增减。
 */
public abstract class CriticalDamageIBuff extends NumIBuff {
    public CriticalDamageIBuff(int last, String prefix, double criticalDamage, Entity src) {
        super(last, prefix + "-暴伤", criticalDamage, src);
    }

    @Override
    public final double getCriticalDamage() {
        return value;
    }
}
