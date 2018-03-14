package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减速。
 */
public abstract class ReduceSpeed extends BaseCommonIBuff implements DispellableDebuff {
    private final double speed;

    public ReduceSpeed(int last, String name, Entity src, double speed) {
        super(last, name, src);
        this.speed = -speed;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
}
