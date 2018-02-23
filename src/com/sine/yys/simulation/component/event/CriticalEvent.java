package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;

/**
 * 暴击事件。必须是造成伤害的暴击，不算护盾。
 */
public class CriticalEvent extends BaseVectorEvent implements Event {
    public CriticalEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
