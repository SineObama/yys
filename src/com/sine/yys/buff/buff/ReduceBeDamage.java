package com.sine.yys.buff.buff;

import com.sine.yys.buff.CriticalDamageIBuff;
import com.sine.yys.inter.Entity;

/**
 * 减伤。
 */
public abstract class ReduceBeDamage extends CriticalDamageIBuff implements DispellableBuff {
    /**
     * @param last         持续回合数。必须为正。
     * @param prefix       名称前缀。
     * @param reduceDamage 减伤百分比。
     * @param src          来源式神。
     */
    protected ReduceBeDamage(int last, String prefix, double reduceDamage, Entity src) {
        super(last, prefix + "-减少", -reduceDamage, src);
    }
}
