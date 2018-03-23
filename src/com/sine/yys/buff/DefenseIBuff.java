package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 防御增减（按百分比）。
 */
public abstract class DefenseIBuff extends NumIBuff {
    public DefenseIBuff(int last, String prefix, double defPct, Entity src) {
        super(last, prefix + "-防御", defPct, src);
    }

    @Override
    public final double getDefPct() {
        return value;
    }
}
