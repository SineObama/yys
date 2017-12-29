package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 一个式神实体站位。
 * 死后复活、召唤物等等。
 */
public interface Position {
    Entity getCurrent();
    Entity getSource();
}
