package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.inter.Controller;
import com.sine.yys.simulation.component.inter.Entity;

/**
 * 行动前事件。
 */
public class BeforeActionEvent extends BaseEntityEvent implements Event {
    public BeforeActionEvent(Controller controller, Entity entity) {
        super(controller, entity);
    }
}
