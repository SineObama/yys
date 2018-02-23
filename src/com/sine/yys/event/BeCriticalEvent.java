package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 被暴击事件。
 */
public class BeCriticalEvent extends BaseVectorEvent implements Event {
    public BeCriticalEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
