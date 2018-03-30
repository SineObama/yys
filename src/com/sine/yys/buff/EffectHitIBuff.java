package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 效果命中增减。
 */
public abstract class EffectHitIBuff extends NumIBuff {
    public EffectHitIBuff(int last, String prefix, double effectHit, Entity src) {
        super(last, prefix + "-效果命中", effectHit, src);
    }

    @Override
    public final double getEffectHit() {
        return value;
    }
}
