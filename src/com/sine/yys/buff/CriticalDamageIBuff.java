package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 暴伤增减buff。
 */
public abstract class CriticalDamageIBuff extends NumIBuff {
    /**
     * @param last           持续回合数。必须为正。
     * @param prefix         名称前缀。
     * @param criticalDamage 暴伤。
     * @param src            来源式神。
     */
    public CriticalDamageIBuff(int last, String prefix, double criticalDamage, Entity src) {
        super(last, prefix + "-暴伤", criticalDamage, src);
    }

    @Override
    public final double getCriticalDamage() {
        return value;
    }
}
