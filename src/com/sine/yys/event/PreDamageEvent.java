package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 造成伤害前事件。
 * <p>
 * 用途：伤害加成（破势、心眼、鸣屋等）。
 */
public class PreDamageEvent extends BaseAttackEvent {
    public PreDamageEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
