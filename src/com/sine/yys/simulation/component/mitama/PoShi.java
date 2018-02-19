package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.model.InitContext;
import com.sine.yys.simulation.component.model.effect.DamageChangeByLife;

/**
 * 破势。
 */
public class PoShi extends BaseMitama implements Mitama {
    private static final DamageChangeByLife effect = new DamageChangeByLife(0.7, 1, 1.4, "破势");

    @Override
    public String getName() {
        return "破势";
    }

    @Override
    public void doInit(InitContext context) {
        context.getSelf().getEventController().add(effect);
    }
}
