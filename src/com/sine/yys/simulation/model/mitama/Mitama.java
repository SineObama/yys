package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Named;

/**
 * 御魂。
 */
public interface Mitama extends Named {
    void init(InitContext context);
}
