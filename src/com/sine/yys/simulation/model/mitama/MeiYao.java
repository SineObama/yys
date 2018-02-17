package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.effect.HunLuanEffect;

/**
 * 魅妖。
 */
public class MeiYao extends BaseMitama implements Mitama {
    private static final HunLuanEffect effect = new HunLuanEffect(0.25, "魅妖");

    @Override
    public String getName() {
        return "魅妖";
    }

    @Override
    public void doInit(InitContext context) {
        getSelf().getEventController().add(effect);
    }
}
