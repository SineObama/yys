package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 受到伤害事件。
 * <p>
 * 多段攻击也算。
 * <p>
 * 用途：
 * 1. 受到伤害时回复鬼火（等）；
 * 2. 薙魂时受到伤害触发小僧被动；
 * 3. 薙魂时受到伤害触发犬神反击；
 */
public class BeDamageEvent extends BaseAttackEvent {
    private double damage;

    public BeDamageEvent(Entity entity, Entity target, AttackType type, double damage) {
        super(entity, target, type);
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
