package com.sine.yys.buff.debuff;

import com.sine.yys.inter.Entity;

/**
 * 按最大生命百分比的持续伤害。
 */
public abstract class PctDoT extends BaseDoT implements DispellableDebuff {
    private final double pct;

    protected PctDoT(int last, String name, double lifePct, Entity src) {
        super(last, name + "-生命百分比", src);
        this.pct = lifePct;
    }

    @Override
    public final double handle(Entity self) {
        return self.getMaxLife() * pct;
    }
}
