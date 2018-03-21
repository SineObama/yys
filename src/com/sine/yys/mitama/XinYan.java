package com.sine.yys.mitama;

import com.sine.yys.effect.DamageChangeByLife;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 心眼。
 */
public class XinYan extends BaseMitama {
    private final DamageChangeByLife effect = new DamageChangeByLife(getMinLifePct(), getMaxLifePct(), getCoefficient(), getName());

    public double getMinLifePct() {
        return 0.0;
    }

    public double getMaxLifePct() {
        return 0.3;
    }

    public double getCoefficient() {
        return 1.5;
    }

    @Override
    public String getName() {
        return "心眼";
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(effect);
    }
}