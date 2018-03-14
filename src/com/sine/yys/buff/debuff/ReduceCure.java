package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减疗。
 */
public class ReduceCure extends BaseCommonIBuff {
    private final double cure;

    public ReduceCure(int last, String name, Entity src, double cure) {
        super(last, name, src);
        this.cure = -cure;
    }

    @Override
    public double getCure() {
        return cure;
    }
}
