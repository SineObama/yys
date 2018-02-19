package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.effect.AddDebuffEffect;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.DamageEvent;

/**
 * 附加debuff的御魂效果通用逻辑。
 * 如魅妖、雪幽魂、反枕等。
 * 子类只需要重写getEffect改变效果。
 *
 * @see AddDebuffEffect
 */
public abstract class BaseDebuffMitama extends BaseMitama implements Mitama {

    abstract AddDebuffEffect getEffect();

    @Override
    public void init(InitContext context) {
        context.getSelf().getEventController().add(new AddDebuffHandler(context.getSelf()));
    }

    class AddDebuffHandler extends SealableMitamaHandler implements EventHandler<DamageEvent> {
        AddDebuffHandler(Entity self) {
            super(self);
        }

        @Override
        public void handle(DamageEvent event) {
            getEffect().handle(event);
        }
    }
}
