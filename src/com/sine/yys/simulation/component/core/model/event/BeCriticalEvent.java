package com.sine.yys.simulation.component.core.model.event;

import com.sine.yys.simulation.component.inter.Entity;

/**
 * 被暴击事件。
 */
public class BeCriticalEvent implements Event {
    private Entity self;
    private Entity target;

    public BeCriticalEvent(Entity self, Entity target) {
        this.self = self;
        this.target = target;
    }

    /**
     * 受到暴击的实体。
     */
    public Entity getSelf() {
        return self;
    }

    /**
     * 实施伤害的实体。
     */
    public Entity getTarget() {
        return target;
    }
}
