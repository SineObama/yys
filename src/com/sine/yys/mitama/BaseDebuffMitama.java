package com.sine.yys.mitama;

import com.sine.yys.effect.AddDebuffEffect;
import com.sine.yys.event.DamageEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.Mitama;

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
    public void init(Controller controller, Entity self) {
        self.getEventController().add(new AddDebuffHandler(self));
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
