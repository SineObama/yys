package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 速度。
 */
public abstract class SpeedIBuff extends NumIBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param prefix 名称前缀。
     * @param speed  速度。
     * @param src    来源式神。
     */
    public SpeedIBuff(int last, String prefix, double speed, Entity src) {
        super(last, prefix + "-速度", speed, src);
    }

    @Override
    public final double getSpeed() {
        return value;
    }
}
