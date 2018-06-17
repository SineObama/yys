package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 受到伤害事件（多段攻击也算）。
 * <p>
 * 用途：
 * * 奴良陆生受到伤害反击；
 * * 受到伤害时减免效果；
 */
public class BeDamageEvent extends BaseAttackEvent {
    public BeDamageEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
