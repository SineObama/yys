package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 受到伤害事件。
 * 多段攻击也算。
 * 记录双方。
 * <p>
 * 目前想到的用途：
 * 1. 受到伤害时回复鬼火（等）；
 * 2. 薙魂时受到伤害触发小僧被动；
 * 3. 薙魂时受到伤害触发犬神反击；
 */
public class BeDamageEvent extends BaseAttackEvent implements Event {
    public BeDamageEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
