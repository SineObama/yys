package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 防御减少debuff。
 */
public class ReduceDefense extends BaseCommonIBuff implements DispellableDebuff {
    private final double defPct;

    /**
     * @param last      持续回合数。必须为正。
     * @param name      buff名称。
     * @param reducePct 防御减少百分比。
     */
    public ReduceDefense(int last, String name, double reducePct, Entity src) {
        super(last, name, src);
        this.defPct = -reducePct;
    }

    @Override
    public double getDefPct() {
        return defPct;
    }
}
