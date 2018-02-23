package com.sine.yys.inter;

import com.sine.yys.info.Target;

import java.util.List;

/**
 * 阵营。
 * 管理一个阵营中的位置和式神，还有阵营事件。
 * 主要用作数据结构，不直接包含战斗逻辑。
 * <p>
 * 一场战斗中通常分为2个阵营。
 */
public interface Camp extends Target {
    List<Entity> getAllAlive();

    List<Entity> getAllShikigami();

    boolean contain(Entity entity);

    Position getPosition(Entity entity);

    EventController getEventController();

    Entity randomTarget();

    Camp getOpposite();
}
