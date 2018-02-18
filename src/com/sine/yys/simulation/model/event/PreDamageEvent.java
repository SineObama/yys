package com.sine.yys.simulation.model.event;

import com.sine.yys.simulation.model.Entity;

/**
 * 造成真实伤害前事件。目前用于伤害加成。
 */
public class PreDamageEvent extends BaseVectorEvent implements Event {
    private double coefficient = 1.0;

    public PreDamageEvent(Entity self, Entity target) {
        super(self, target);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void multiplyCoefficient(double coefficient) {
        this.coefficient *= coefficient;
    }
}
