package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.effect.AddDebuffEffect;
import com.sine.yys.simulation.component.event.DamageEvent;
import com.sine.yys.simulation.event.EventHandler;

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
    public void init(Controller controller) {
        controller.getEventController().add(new AddDebuffHandler(controller));
    }

    class AddDebuffHandler extends SealableMitamaHandler implements EventHandler<DamageEvent> {
        AddDebuffHandler(Controller controller) {
            super(controller);
        }

        @Override
        public void handle(DamageEvent event) {
            getEffect().handle(event);
        }
    }
}
