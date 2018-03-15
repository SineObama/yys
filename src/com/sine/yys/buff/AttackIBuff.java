package com.sine.yys.buff;

import com.sine.yys.buff.buff.DispellableBuff;
import com.sine.yys.inter.Entity;

/**
 * 攻击增减buff（按百分比）。
 */
public abstract class AttackIBuff extends NumIBuff implements DispellableBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param prefix 名称。
     * @param atkPct 防御百分比。
     * @param src    来源式神。
     */
    public AttackIBuff(int last, String prefix, double atkPct, Entity src) {
        super(last, prefix + "-攻击", atkPct, src);
    }

    @Override
    public final double getAtkPct() {
        return value;
    }
}
