package com.sine.yys.effect;

import com.sine.yys.buff.debuff.HunLuan;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.Entity;

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
    public Debuff getDebuff(Entity self) {
        return new HunLuan(self);
    }
}
