package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 事件拓展基类，额外包含一个实体。
 */
public abstract class BaseEntityEvent extends BaseEvent implements Event {
    private final Entity entity;

    public BaseEntityEvent(Controller controller, Entity entity) {
        super(controller);
        this.entity = entity;
    }

    public final Entity getEntity() {
        return entity;
    }
}
