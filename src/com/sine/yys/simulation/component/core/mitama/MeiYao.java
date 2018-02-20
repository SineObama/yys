package com.sine.yys.simulation.component.core.mitama;

import com.sine.yys.simulation.component.core.effect.AddDebuffEffect;
import com.sine.yys.simulation.component.core.effect.HunLuanEffect;
import com.sine.yys.simulation.info.PctEffect;

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
