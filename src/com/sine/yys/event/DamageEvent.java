package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 造成伤害时***。
 */
public class DamageEvent extends BaseVectorEvent implements Event {
    public DamageEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
