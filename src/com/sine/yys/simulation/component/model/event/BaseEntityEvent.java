package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;

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
