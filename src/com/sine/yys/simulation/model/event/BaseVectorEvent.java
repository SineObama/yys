package com.sine.yys.simulation.model.event;

import com.sine.yys.simulation.model.Entity;

/**
 * 包含自身和目标的事件通用逻辑。
 * （定义此为指向性信息。）
 */
public abstract class BaseVectorEvent implements Event {
    private Entity self;
    private Entity target;

    BaseVectorEvent(Entity self, Entity target) {
        this.self = self;
        this.target = target;
    }

    public final Entity getSelf() {
        return self;
    }

    public final Entity getTarget() {
        return target;
    }
}
