package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.effect.Effect;

/**
 * 御魂
 */
public interface Mitama {
    String getName();

    Effect getEffect();

    void init(InitContext context);
}
