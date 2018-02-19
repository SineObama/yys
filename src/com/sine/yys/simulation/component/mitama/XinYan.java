package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.model.effect.DamageChangeByLife;

/**
 * 心眼。
 */
public class XinYan extends BaseMitama implements Mitama {
    private static final DamageChangeByLife effect = new DamageChangeByLife(0, 0.3, 1.5, "心眼");

    @Override
    public String getName() {
        return "心眼";
    }

    @Override
    public void doInit(InitContext context) {
        context.getSelf().getEventController().add(effect);
    }
}
