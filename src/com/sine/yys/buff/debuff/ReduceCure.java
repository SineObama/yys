package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减疗
 */
public class ReduceCure extends BaseCommonIBuff {
    private final double pct;

    public ReduceCure(int last, String name, Entity src, double pct) {
        super(last, name, src);
        this.pct = pct;
    }

    public double getPct() {
        return pct;
    }
}
