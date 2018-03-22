package com.sine.yys.buff.debuff;

import com.sine.yys.buff.SpeedIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减速。
 */
public abstract class ReduceSpeed extends SpeedIBuff implements DispellableDebuff {
    protected ReduceSpeed(int last, String prefix, double reduceSpeed, Entity src) {
        super(last, prefix + "-减少", -reduceSpeed, src);
    }
}
