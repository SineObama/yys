package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.Mitama;
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
    public void init(InitContext context) {
        context.getSelf().getEventController().add(new Handler(context.getSelf()));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<PreDamageEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(PreDamageEvent event) {
            effect.handle(event);
        }
    }
}
