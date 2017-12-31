package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

import java.util.List;

/**
 * 阵营。
 */
public interface Camp extends Target {
    void addEntity(Entity entity);

    List<Entity> getAllAlive();

    boolean contain(Entity entity);

    int getFire();

    /**
     * 鬼火行动条前进一格。
     *
     * @return 行动条进度，1~5。
     */
    int step();

    Position getPosition(Entity entity);
}
