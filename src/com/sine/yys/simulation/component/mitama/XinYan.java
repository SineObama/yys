package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.effect.DamageChangeByLife;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.PreDamageEvent;

/**
 * 心眼。
 */
public class XinYan extends BaseMitama implements Mitama {
    private final DamageChangeByLife effect = new DamageChangeByLife(0, 0.3, 1.5, getName());

    @Override
    public String getName() {
        return "心眼";
    }

    @Override
    public void init(Controller controller) {
        controller.getOwn().getEventController(controller.getSelf()).add(new Handler(controller));
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
