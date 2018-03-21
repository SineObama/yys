package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.debuff.ReduceSpeed;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;

import java.util.Collection;
import java.util.Collections;

/**
 * 椒图-水花弹。
 */
public class ShuiHuaDan extends BaseCommonAttack implements DebuffEffect {
    @Override
    public String getName() {
        return "水花弹";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.25;
    }

    /**
     * 减速概率。
     */
    public double getPct() {
        return 0.3;
    }

    @Override
    public boolean involveHitAndDef() {
        return true;
    }

    @Override
    public Debuff getDebuff(Entity self) {
        return new ReduceSpeed(1, getName(), 10.0, self) {
        };
    }

    @Override
    public Collection<DebuffEffect> getDebuffEffects() {
        return Collections.singleton(this);
    }
}
