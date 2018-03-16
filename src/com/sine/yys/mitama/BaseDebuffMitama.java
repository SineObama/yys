package com.sine.yys.mitama;

import com.sine.yys.event.DamageEvent;
import com.sine.yys.inter.*;

/**
 * 附加debuff的御魂效果通用逻辑。
 * 如魅妖、雪幽魂、反枕等。
 * 子类只需要重写getEffect改变效果。
 */
public abstract class BaseDebuffMitama extends BaseMitama implements DebuffEffect, EventHandler<DamageEvent> {
    @Override
    public final void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    @Override
    public final void handle(DamageEvent event) {
        event.getController().applyDebuff(event.getEntity(), event.getTarget(), this);
    }

    @Override
    public final boolean involveHitAndDef() {
        return true;
    }
}
