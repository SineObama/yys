package com.sine.yys.simulation.model.event;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 行动前事件。
 */
public class BeforeActionEvent implements Event {
    private final Entity target;

    public BeforeActionEvent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }
}
