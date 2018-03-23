package com.sine.yys.mitama;

import com.sine.yys.event.DamageEvent;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

/**
 * 造成伤害时附加debuff的御魂通用逻辑。
 * <p>
 * 如魅妖、雪幽魂、反枕等。
 * <p>
 * 子类只需要定义：
 * 1. {@linkplain DebuffEffect#getDebuff(Entity)}设置效果；
 * 2. {@linkplain DebuffEffect#getPct()}设置概率。
 */
public abstract class BaseDebuffMitama extends BaseSelfMitama implements DebuffEffect, EventHandler<DamageEvent> {
    @Override
    public final void handle(DamageEvent event) {
        getController().applyDebuff(event.getEntity(), event.getTarget(), this);
    }

    @Override
    public final boolean involveHitAndDef() {
        return true;
    }
}
