package com.sine.yys.event;

import com.sine.yys.buff.debuff.Debuff;
import com.sine.yys.inter.Entity;

/**
 * 伤害事件。
 * <p>
 * 用途：造成伤害时附加{@linkplain Debuff}。
 */
public class DamageEvent extends BaseVectorEvent {
    public DamageEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
