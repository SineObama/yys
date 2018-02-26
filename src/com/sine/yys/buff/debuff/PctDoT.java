package com.sine.yys.buff.debuff;

import com.sine.yys.inter.Entity;

/**
 * 镰鼬-胖揍-按最大生命百分比的持续伤害。
 */
public class PctDoT extends BaseDoT implements DoT, DispellableDebuff {
    private final double pct;

    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     * @param pct  生命百分比。
     * @param self
     */
    public PctDoT(int last, String name, double pct, Entity src) {
        super(last, name, src);
        this.pct = pct;
    }

    @Override
    public double handle(Entity entity) {
        return entity.getMaxLife() * pct;
    }
}
