package com.sine.yys.effect;

import com.sine.yys.inter.DebuffEffect;

/**
 * 负面效果基类。
 * 默认受效果命中抵抗影响。
 */
public abstract class BaseDebuffEffect extends BaseEffect implements DebuffEffect {
    private final double pct;

    BaseDebuffEffect(double pct, String name) {
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
