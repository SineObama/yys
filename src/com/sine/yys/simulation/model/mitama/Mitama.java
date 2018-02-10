package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 御魂。
 */
public interface Mitama {
    String getName();

    void init(InitContext context);

    Entity getSelf();
}
