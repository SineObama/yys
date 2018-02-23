package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.inter.Controller;
import com.sine.yys.simulation.component.inter.Entity;

/**
 * 被暴击事件。
 */
public class BeCriticalEvent extends BaseVectorEvent implements Event {
    public BeCriticalEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
