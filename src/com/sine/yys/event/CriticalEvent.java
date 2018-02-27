package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 暴击事件。必须是造成伤害的暴击，不算护盾。
 *
 * @see BeCriticalEvent
 */
public class CriticalEvent extends BaseVectorEvent implements Event {
    public CriticalEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
