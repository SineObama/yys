package com.sine.yys.effect;

import com.sine.yys.buff.debuff.Debuff;
import com.sine.yys.buff.debuff.HunLuan;

/**
 * @see HunLuan
 */
public class HunLuanEffect extends AddDebuffEffect {
    public HunLuanEffect(double pct) {
        super(pct, "混乱效果");
    }

    public HunLuanEffect(double pct, String name) {
        super(pct, name);
    }

    @Override
    public Debuff getDebuff() {
        return new HunLuan();
    }
}
