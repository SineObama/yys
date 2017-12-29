package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;

import java.util.List;

/**
 * 阵营。
 */
public interface Camp {
    void addEntity(Entity entity);
    List<Entity> getAllEntity();
    boolean contain(Entity entity);
    int getFire();
}
