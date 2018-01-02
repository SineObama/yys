package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

public interface ActionContext {
    Entity getSelf();
    Entity getTarget();
    Camp getOwn();
    Camp getEnemy();
}
