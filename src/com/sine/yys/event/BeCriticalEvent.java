package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 必须是造成伤害的暴击，不算护盾。
 *
 * @see CriticalEvent
 */
public class BeCriticalEvent extends BaseVectorEvent implements Event {
    public BeCriticalEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
