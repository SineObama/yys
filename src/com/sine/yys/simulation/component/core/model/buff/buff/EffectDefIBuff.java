package com.sine.yys.simulation.component.core.model.buff.buff;

import com.sine.yys.simulation.component.core.model.buff.NumIBuff;

/**
 * 效果抵抗增减buff。
 */
public class EffectDefIBuff extends NumIBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param defPct 防御百分比。
     */
    public EffectDefIBuff(int last, String name, double defPct) {
        super(last, name, defPct);
    }

    @Override
    public double getEffectDef() {
        return value;
    }
}
