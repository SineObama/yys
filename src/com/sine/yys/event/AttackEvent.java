package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 攻击目标事件。
 * <p>
 * 不需要造成伤害，在造成伤害前触发，多段攻击也算。
 * 用于附加效果。
 */
public class AttackEvent extends BaseVectorEvent {
    private double coefficient = 1.0;

    public AttackEvent(Entity entity, Entity target) {
        super(entity, target);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void multiplyCoefficient(double coefficient) {
        this.coefficient *= coefficient;
    }

}
