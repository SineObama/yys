package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 事件拓展基类，包含2个实体。
 * （定义此为指向性信息。）
 */
public abstract class BaseVectorEvent extends BaseEntityEvent implements Event {
    private final Entity target;

    public BaseVectorEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity);
        this.target = target;
    }

    public final Entity getTarget() {
        return target;
    }
}
