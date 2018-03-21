package com.sine.yys.mitama;

import com.sine.yys.effect.DamageChangeByLife;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 破势。
 */
public class PoShi extends BaseMitama {
    private final DamageChangeByLife effect = new DamageChangeByLife(getMinLifePct(), getMaxLifePct(), getCoefficient(), getName());

    public double getMinLifePct() {
        return 0.7;
    }

    public double getMaxLifePct() {
        return 1.0;
    }

    public double getCoefficient() {
        return 1.4;
    }

    @Override
    public String getName() {
        return "破势";
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(effect);
    }
}
