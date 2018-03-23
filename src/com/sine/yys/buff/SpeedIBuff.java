package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 速度增减。
 */
public abstract class SpeedIBuff extends NumIBuff {
    public SpeedIBuff(int last, String prefix, double speed, Entity src) {
        super(last, prefix + "-速度", speed, src);
    }

    @Override
    public final double getSpeed() {
        return value;
    }
}
