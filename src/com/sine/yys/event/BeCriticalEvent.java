package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 被伤害暴击事件（必须造成伤害）。
 */
public class BeCriticalEvent extends BaseVectorEvent {
    public BeCriticalEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
