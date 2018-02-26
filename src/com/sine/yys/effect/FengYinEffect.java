package com.sine.yys.effect;

import com.sine.yys.buff.debuff.FengYin;
import com.sine.yys.inter.Debuff;

/**
 * 封印御魂和被动。
 */
public class FengYinEffect extends BaseDebuffEffect {
    private final int last;

    public FengYinEffect(double pct, int last, String name) {
        super(pct, name);
        this.last = last;
    }

    @Override
    public Debuff getDebuff() {
        return new FengYin(last);
    }
}
