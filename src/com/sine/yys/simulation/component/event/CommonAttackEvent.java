package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.model.entity.Entity;

public class CommonAttackEvent implements Event {
    Entity target;

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
