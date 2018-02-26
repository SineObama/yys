package com.sine.yys.effect;

import com.sine.yys.buff.debuff.PctDoT;
import com.sine.yys.buff.debuff.ReduceDefense;
import com.sine.yys.buff.debuff.XuanYun;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.RandUtil;

import java.util.Arrays;

/**
 * 镰鼬-胖揍。
 */
public class PangZouEffect extends BaseDebuffEffect {
    public PangZouEffect(double pct) {
        super(pct, "胖揍效果");
    }

    public Debuff getDebuff(Entity self) {
        return RandUtil.choose(Arrays.asList(new ReduceDefense(2, "破防", 0.3, self), new XuanYun(self), new PctDoT(2, "镰鼬持续伤害", 0.05, self)));
    }
}
