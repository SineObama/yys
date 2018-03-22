package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 事件拓展基类，额外包含一个实体。
 */
public abstract class BaseEntityEvent extends BaseEvent {
    private final Entity entity;

    BaseEntityEvent(Entity entity) {
        this.entity = entity;
    }

    public final Entity getEntity() {
        return entity;
    }
}
