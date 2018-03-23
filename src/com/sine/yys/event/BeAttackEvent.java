package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 阵营或式神受击事件。
 * <p>
 * 用途：
 * 1. 被攻击时反击；
 * 2. 被攻击时**（多段攻击不重复计算）。
 */
public class BeAttackEvent extends BaseAttackEvent {
    public BeAttackEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
