package com.sine.yys.effect;

import com.sine.yys.inter.DebuffEffect;

/**
 * 一般的附加负面效果。
 * 默认受效果命中影响。
 */
public abstract class BaseDebuffEffect extends BaseEffect implements DebuffEffect {
    private final double pct;

    protected BaseDebuffEffect(double pct, String name) {
        super(name);
        this.pct = pct;
    }

    @Override
    public final double getPct() {
        return pct;
    }

    @Override
    public boolean involveHitAndDef() {
        return true;
    }
}
