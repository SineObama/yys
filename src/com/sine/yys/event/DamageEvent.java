package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 造成伤害时***。
 * 用于附加debuff。
 */
public class DamageEvent extends BaseVectorEvent implements Event {
    private final Controller controller;

    public DamageEvent(Controller controller, Entity entity, Entity target) {
        super(entity, target);
        this.controller = controller;
    }

    public final Controller getController() {
        return controller;
    }
}
