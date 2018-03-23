package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 攻击增减（按百分比）。
 */
public abstract class AttackIBuff extends NumIBuff {
    public AttackIBuff(int last, String prefix, double atkPct, Entity src) {
        super(last, prefix + "-攻击", atkPct, src);
    }

    @Override
    public final double getAtkPct() {
        return value;
    }
}
