package com.sine.yys.skill.commonattack;

import com.sine.yys.buff.debuff.ReduceSpeed;
import com.sine.yys.effect.BaseDebuffEffect;
import com.sine.yys.inter.Debuff;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.PctEffect;

import java.util.Collection;
import java.util.Collections;

/**
 * 椒图-水花弹。
 */
public class ShuiHuaDan extends BaseCommonAttack implements PctEffect {
    private final DebuffEffect effect = new BaseDebuffEffect(getPct(), getName()) {
        @Override
        public Debuff getDebuff(Entity self) {
            return new ReduceSpeed(getLast(), getName(), getReduceSpeed(), self) {
            };
        }
    };

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
        return Collections.singleton(effect);
    }
}
