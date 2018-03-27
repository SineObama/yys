package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 被击杀事件。
 *
 * @see KillEvent
 */
public class BeKillEvent extends BaseAttackEvent {
    public BeKillEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
