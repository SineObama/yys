package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 效果抵抗增减。
 */
public abstract class EffectDefIBuff extends NumIBuff {
    public EffectDefIBuff(int last, String prefix, double effectDef, Entity src) {
        super(last, prefix + "-效果抵抗", effectDef, src);
    }

    @Override
    public final double getEffectDef() {
        return value;
    }
}
