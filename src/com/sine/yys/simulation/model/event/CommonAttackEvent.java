package com.sine.yys.simulation.model.event;

import com.sine.yys.simulation.model.Entity;

/**
 * 己方式神普攻事件，保存在阵营中。
 */
public class CommonAttackEvent implements Event {
    private Entity self;
    private Entity target;

    public CommonAttackEvent(Entity self, Entity target) {
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
