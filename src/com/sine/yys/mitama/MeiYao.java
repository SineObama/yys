package com.sine.yys.mitama;

import com.sine.yys.effect.AddDebuffEffect;
import com.sine.yys.effect.HunLuanEffect;
import com.sine.yys.inter.Mitama;

/**
 * 魅妖。
 */
public class MeiYao extends BaseDebuffMitama implements Mitama {
    @Override
    public String getName() {
        return "魅妖";
    }

    public double getPct() {
        return 0.25;
    }

    @Override
    AddDebuffEffect getEffect() {
        return new HunLuanEffect(getPct(), getName());
    }
}
