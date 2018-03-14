package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.debuff.PctDoT;
import com.sine.yys.buff.debuff.ReduceDefense;
import com.sine.yys.buff.debuff.XuanYun;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.RandUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 镰鼬-胖揍。
 */
public class PangZou extends BaseCommonAttack implements DebuffEffect {
    @Override
    public String getName() {
        return "胖揍";
    }

    @Override
    public double getCoefficient() {
        return 1.0 * 1.2;
    }

    /**
     * @return 附加效果概率
     */
    public double getPct() {
        return 0.2;
    }

    @Override
    public boolean involveHitAndDef() {
        return true;
    }

    @Override
    public Debuff getDebuff(Entity self) {
        return RandUtil.choose(Arrays.asList(new ReduceDefense(2, "破防", 0.3, self), new XuanYun(self), new PctDoT(2, "镰鼬持续伤害", 0.05, self)));
    }

    @Override
    protected Collection<DebuffEffect> getDebuffEffects() {
        return Collections.singleton(this);
    }
}
