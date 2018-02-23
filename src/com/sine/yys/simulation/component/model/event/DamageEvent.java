package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;

/**
 * 造成伤害时***。
 */
public class DamageEvent extends BaseVectorEvent implements Event {
    public DamageEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
