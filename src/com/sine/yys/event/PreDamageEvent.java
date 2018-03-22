package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 造成伤害前事件。
 * <p>
 * 用途：伤害加成（破势、心眼、鸣屋等）。
 */
public class PreDamageEvent extends BaseVectorEvent {
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
