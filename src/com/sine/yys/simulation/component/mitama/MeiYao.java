package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.effect.AddDebuffEffect;
import com.sine.yys.simulation.component.effect.HunLuanEffect;
import com.sine.yys.simulation.component.effect.PctEffect;

/**
 * 魅妖。
 */
public class MeiYao extends BaseDebuffMitama implements Mitama, PctEffect {
    @Override
    public String getName() {
        return "魅妖";
    }

    @Override
    public double getPct() {
        return 0.25;
    }

    @Override
    AddDebuffEffect getEffect() {
        return new HunLuanEffect(getPct(), getName());
    }
}
