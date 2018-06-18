package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.control.XuanYun;
import com.sine.yys.buff.debuff.PctDoT;
import com.sine.yys.buff.debuff.ReduceDefense;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;
import com.sine.yys.util.RandUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 镰鼬-胖揍。
 */
public class PangZou extends BaseCommonAttack implements DebuffEffect {
    @Override
    public double getPct() {
        return 0.2;
    }

    /**
     * @return 破防数值。
     */
    public double getReduceDefensePct() {
        return 0.3;
    }

    /**
     * @return 损失生命百分比。
     */
    public double getReduceLifePct() {
        return 0.05;
    }

    /**
     * @return 负面效果持续回合。
     */
    public int getLast() {
        return 2;
    }

    @Override
    public String getName() {
        return "胖揍";
    }

    @Override
    public double getCoefficient() {
        return 1.0 * 1.2;
    }

    @Override
    public Collection<DebuffEffect> getDebuffEffects() {
        return Collections.singleton(this);
    }

    @Override
    public IBuff getDebuff(Entity self) {
        return RandUtil.choose(Arrays.asList(
                new ReduceDefense(getLast(), getName(), getReduceDefensePct(), self) {
                }, new XuanYun(self), new PctDoT(getLast(), getName(), getReduceLifePct(), self) {
                }));
    }
}
