package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 暴击事件。必须是造成伤害时的暴击。
 *
 * @see BeCriticalEvent
 */
public class CriticalEvent extends BaseVectorEvent implements Event {
    public CriticalEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
