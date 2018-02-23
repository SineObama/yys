package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;

/**
 * 行动前事件。
 */
public class BeforeActionEvent extends BaseEntityEvent implements Event {
    public BeforeActionEvent(Controller controller, Entity entity) {
        super(controller, entity);
    }
}
