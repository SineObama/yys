package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 效果抵抗增减buff。
 */
public abstract class EffectDefIBuff extends NumIBuff {
    /**
     * @param last      持续回合数。必须为正。
     * @param prefix    名称前缀。
     * @param effectDef 效果抵抗。
     * @param src       来源式神。
     */
    public EffectDefIBuff(int last, String prefix, double effectDef, Entity src) {
        super(last, prefix + "-效果抵抗", effectDef, src);
    }

    @Override
    public final double getEffectDef() {
        return value;
    }
}
