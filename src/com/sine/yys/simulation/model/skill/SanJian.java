package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 伞剑
 */
public class SanJian extends CommonAttack {
    public SanJian(Entity self) {
        super(self);
    }

    @Override
    public String getName() {
        return "伞剑";
    }

    @Override
    public double getCoefficient() {
        return 0.8;
    }

    public double ignoreDefendPct() {
        return 0.2;
    }

    @Override
    public void apply(Controller controller) {}
}
