package com.sine.yys.event;

import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加伤害效果事件。
 * <p>
 * 在攻击前触发（未必会造成伤害），监听器把造成伤害时会产生的debuff效果（主要是雪幽魂等御魂）添加进列表，后续效果才被调用。
 */
public class AddDamageEffectEvent extends BaseEntityEvent {
    private final List<DebuffEffect> effects = new ArrayList<>();

    public AddDamageEffectEvent(Entity entity) {
        super(entity);
    }

    public List<DebuffEffect> getEffects() {
        return effects;
    }
}
