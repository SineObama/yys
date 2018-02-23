package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 行动前事件。
 */
public class BeforeActionEvent extends BaseEntityEvent implements Event {
    public BeforeActionEvent(Controller controller, Entity entity) {
        super(controller, entity);
    }
}
