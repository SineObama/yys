package com.sine.yys.mitama;

import com.sine.yys.effect.DamageChangeByLife;
import com.sine.yys.event.PreDamageEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.EventHandler;

/**
 * 破势。
 */
public class PoShi extends BaseMitama implements Mitama {
    private final DamageChangeByLife effect = new DamageChangeByLife(0.7, 1, 1.4, getName());

    @Override
    public String getName() {
        return "破势";
    }

    @Override
    public void init(Controller controller) {
        controller.getEventController().add(new Handler(controller));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<PreDamageEvent> {
        Handler(Controller controller) {
            super(controller);
        }

        @Override
        public void handle(PreDamageEvent event) {
            effect.handle(event);
        }
    }
}
