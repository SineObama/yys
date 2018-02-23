package com.sine.yys.inter;

import com.sine.yys.info.Target;

import java.util.List;

/**
 * 阵营。
 * 一场战斗中通常分为2个阵营。
 */
public interface Camp extends Target {
    List<Entity> getAllAlive();

    List<Entity> getAllShikigami();

    boolean contain(Entity entity);

    int getIncrease();

    Position getPosition(Entity entity);

    EventController getEventController();

    EventController getEventController(Entity entity);

    Entity randomTarget();

    Camp getOpposite();
}
