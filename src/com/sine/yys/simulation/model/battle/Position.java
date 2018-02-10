package com.sine.yys.simulation.model.battle;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 一个式神实体站位。
 * 用于死后复活、召唤物放置等等。
 */
public interface Position {
    Entity getCurrent();

    Entity getSource();

    boolean isDead();

    void setDead(boolean dead);
}
