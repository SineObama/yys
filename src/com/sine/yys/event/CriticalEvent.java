package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 伤害暴击事件（必须造成伤害）。
 */
public class CriticalEvent extends BaseAttackEvent {
    public CriticalEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
