package com.sine.yys.inter;

import com.sine.yys.inter.base.Target;

import java.util.List;

/**
 * 阵营。
 * <p>
 * 存储一个阵营中的位置和式神，也包含事件。
 * 主要用作数据结构，不直接包含战斗逻辑。
 */
public interface Camp extends Target {
    List<? extends Entity> getAllAlive();

    /**
     * @return 可复活的式神。
     */
    List<? extends ShikigamiEntity> getRevivable();

    List<? extends ShikigamiEntity> getAllShikigami();

    /**
     * 获取血量百分比最低的式神。
     */
    ShikigamiEntity getLeastLifeShikigami();

    boolean contain(Entity entity);

    Position getPosition(Entity entity);

    /**
     * 根据位置上原本的式神查找。
     */
    Position getPositionBySrc(Entity entity);

    EventController getEventController();

    Entity randomTarget();

    /**
     * @return 敌对阵营。
     */
    Camp getOpposite();
}
