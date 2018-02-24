package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.Entity;

/**
 * 按最大生命百分比的持续伤害。
 */
public abstract class PctDoT extends BaseIBuff implements DoT, DispellableDebuff {
    private final double pct;

    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     * @param pct  生命百分比。
     */
    public PctDoT(int last, String name, double pct) {
        super(last, name);
        this.pct = pct;
    }

    @Override
    public double handle(Entity entity) {
        return entity.getMaxLife() * pct;
    }
}
