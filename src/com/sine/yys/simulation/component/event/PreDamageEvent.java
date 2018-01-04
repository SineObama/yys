package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 造成真实伤害前事件。目前只有伤害加系数的用法。
 */
public class PreDamageEvent implements Event {
    private double coefficient = 1.0;
    private Entity target;

    public double getCoefficient() {
        return coefficient;
    }

    public void multiplyCoefficient(double coefficient) {
        this.coefficient *= coefficient;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
