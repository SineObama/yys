package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.camp.position.Position;
import com.sine.yys.simulation.component.model.EventController;
import com.sine.yys.simulation.info.Target;

import java.util.List;

/**
 * 阵营。
 * 一场战斗中通常分为2个阵营。
 */
public interface Camp extends Target {
    void init(InitContext context);

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
