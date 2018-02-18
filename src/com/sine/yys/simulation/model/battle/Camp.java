package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.component.event.EventController;
import com.sine.yys.simulation.info.Target;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.entity.Shikigami;

import java.util.List;

/**
 * 阵营。
 * 一场战斗中通常分为2个阵营。
 */
public interface Camp extends Target {
    void addEntity(Entity entity);

    List<Entity> getAllAlive();

    List<Shikigami> getAllShikigami();

    boolean contain(Entity entity);

    int getIncrease();

    Position getPosition(Entity entity);

    EventController getEventController();

    Entity randomTarget();

    Camp getOpposite();
}
