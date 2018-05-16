package com.sine.yys.event;

import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;

import java.util.List;

/**
 * 在添加debuff效果后截取，用于霜天之织（包括去除雪幽魂效果）。
 *
 * @see AddDamageEffectEvent
 */
public class AfterAddDamageEffectEvent extends BaseVectorEvent {
    private final List<DebuffEffect> effects;
    private double coefficient = 1.0;

    public AfterAddDamageEffectEvent(Entity entity, Entity target, List<DebuffEffect> effects) {
        super(entity, target);
        this.effects = effects;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void multiplyCoefficient(double coefficient) {
        this.coefficient *= coefficient;
    }

    public List<DebuffEffect> getEffects() {
        return effects;
    }
}
