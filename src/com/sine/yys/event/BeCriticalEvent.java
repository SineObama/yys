package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 必须是造成伤害时的暴击。
 *
 * @see CriticalEvent
 */
public class BeCriticalEvent extends BaseVectorEvent implements Event {
    public BeCriticalEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
