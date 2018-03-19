package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 造成伤害时***。
 * 用于附加debuff。
 */
public class DamageEvent extends BaseVectorEvent implements Event {
    public DamageEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
