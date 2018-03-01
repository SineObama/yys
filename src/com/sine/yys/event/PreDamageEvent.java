package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 造成真实伤害前事件。目前用于伤害加成。
 * 目前用于御魂（破势、心眼等）的伤害加成。
 */
public class PreDamageEvent extends BaseVectorEvent implements Event {
    private double coefficient = 1.0;

    public PreDamageEvent(Entity entity, Entity target) {
        super(entity, target);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void multiplyCoefficient(double coefficient) {
        this.coefficient *= coefficient;
    }
}
