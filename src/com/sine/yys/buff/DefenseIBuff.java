package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 防御增减buff（按百分比）。
 */
public abstract class DefenseIBuff extends NumIBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param prefix 名称前缀。
     * @param defPct 防御百分比。
     * @param src    来源式神。
     */
    public DefenseIBuff(int last, String prefix, double defPct, Entity src) {
        super(last, prefix + "-防御", defPct, src);
    }

    @Override
    public final double getDefPct() {
        return value;
    }
}
