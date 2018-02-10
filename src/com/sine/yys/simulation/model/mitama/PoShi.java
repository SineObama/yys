package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.component.event.PreDamageEvent;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.effect.DamageChangeByLife;
import com.sine.yys.simulation.util.Msg;

/**
 * 破势。
 */
public class PoShi extends BaseMitama implements Mitama, EventHandler<PreDamageEvent> {
    private static final DamageChangeByLife effect = new DamageChangeByLife(0.7, 1, 1.4);

    @Override
    public String getName() {
        return "破势";
    }

    @Override
    public void handle(PreDamageEvent context, Controller controller) {
        if (effect.judge(context.getTarget())) {
            log.info(Msg.trigger(this));
            context.multiplyCoefficient(effect.getCoefficient());
        }
    }

    @Override
    public void init(InitContext context) {
        super.init(context);
        context.getSelf().getEventController().add(PreDamageEvent.class, this);
    }
}
