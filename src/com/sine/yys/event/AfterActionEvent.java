package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 行动后事件。目前用在实体中。
 */
public class AfterActionEvent extends BaseEntityEvent implements Event {
    public AfterActionEvent(Controller controller, Entity entity) {
        super(controller, entity);
    }
}
