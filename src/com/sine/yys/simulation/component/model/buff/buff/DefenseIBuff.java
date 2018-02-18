package com.sine.yys.simulation.component.model.buff.buff;

import com.sine.yys.simulation.component.model.buff.NumIBuff;

/**
 * 防御增减buff（按百分比）。
 */
public class DefenseIBuff extends NumIBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param defPct 防御百分比。
     */
    public DefenseIBuff(int last, String name, double defPct) {
        super(last, name, defPct);
    }

    @Override
    public double getDefPct() {
        return value;
    }
}
