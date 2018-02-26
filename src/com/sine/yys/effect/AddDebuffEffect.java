package com.sine.yys.effect;

import com.sine.yys.event.DamageEvent;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.EventHandler;

/**
 * 造成伤害时概率附加效果。
 */
public abstract class AddDebuffEffect extends BaseDebuffEffect implements DebuffEffect, EventHandler<DamageEvent> {
    AddDebuffEffect(double pct, String name) {
        super(pct, name);
    }

    @Override
    public void handle(DamageEvent event) {
        event.getController().applyDebuff(event.getEntity(), event.getTarget(), this);
    }
}
