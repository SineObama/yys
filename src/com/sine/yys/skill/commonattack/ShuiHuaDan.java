package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.debuff.ReduceSpeed;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

import java.util.Collection;
import java.util.Collections;

/**
 * 椒图-水花弹。
 */
public class ShuiHuaDan extends BaseCommonAttack implements DebuffEffect {
    @Override
    public double getPct() {
        return 0.3;
    }

    public double getReduceSpeed() {
        return 10.0;
    }

    public int getLast() {
        return 1;
    }

    @Override
    public String getName() {
        return "水花弹";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.25;
    }

    @Override
    public Collection<DebuffEffect> getDebuffEffects() {
        return Collections.singleton(this);
    }

    @Override
    public IBuff getDebuff(Entity self) {
        return new ReduceSpeed(getLast(), getName(), getReduceSpeed(), self) {
        };
    }
}
