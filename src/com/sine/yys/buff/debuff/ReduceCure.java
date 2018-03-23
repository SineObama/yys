package com.sine.yys.buff.debuff;

import com.sine.yys.buff.NumIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减疗。
 */
public class ReduceCure extends NumIBuff implements DispellableDebuff {
    public ReduceCure(int last, String prefix, Entity src, double reduceCure) {
        super(last, prefix + "-减疗", -reduceCure, src);
    }

    @Override
    public double getCure() {
        return value;
    }
}
