package com.sine.yys.buff.debuff;

import com.sine.yys.buff.SpeedIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减速。
 */
public abstract class ReduceSpeed extends SpeedIBuff implements DispellableDebuff {
    /**
     * @param last        持续回合数。必须为正。
     * @param prefix      名称前缀。
     * @param reduceSpeed 减少速度。
     * @param src         来源式神。
     */
    protected ReduceSpeed(int last, String prefix, double reduceSpeed, Entity src) {
        super(last, prefix + "-减少", -reduceSpeed, src);
    }
}
