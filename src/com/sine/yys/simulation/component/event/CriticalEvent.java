package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 造成伤害的暴击，不算护盾。
 */
public class CriticalEvent implements Event {
    private Entity self;
    private Entity target;

    public CriticalEvent(Entity self, Entity target) {
        this.self = self;
        this.target = target;
    }

    public Entity getSelf() {
        return self;
    }

    public Entity getTarget() {
        return target;
    }
}
