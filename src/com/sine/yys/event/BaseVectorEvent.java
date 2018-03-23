package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 事件拓展基类，包含2个实体。
 */
public abstract class BaseVectorEvent extends BaseEntityEvent {
    private final Entity target;

    BaseVectorEvent(Entity entity, Entity target) {
        super(entity);
        this.target = target;
    }

    public final Entity getTarget() {
        return target;
    }
}
