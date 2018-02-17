package com.sine.yys.simulation.model.event;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 造成伤害时***。
 */
public class DamageEvent extends BaseVectorEvent implements Event {
    public DamageEvent(Entity self, Entity target) {
        super(self, target);
    }
}
