package com.sine.yys.simulation.model;

import com.sine.yys.simulation.info.Target;

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
