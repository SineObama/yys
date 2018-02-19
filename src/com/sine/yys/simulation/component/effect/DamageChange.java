package com.sine.yys.simulation.component.effect;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.PreDamageEvent;
import com.sine.yys.simulation.util.Msg;

/**
 * 伤害增减效果。
 * 包括根据目标生命百分比（破势、心眼）、根据buff（鸣屋）等。
 * 子类重写judge函数以进行判断。
 */
public abstract class DamageChange extends BaseEffect implements Effect, EventHandler<PreDamageEvent> {
    DamageChange(String name) {
        super(name);
    }

    /**
     * 根据目标判断伤害加成系数。
     *
     * @return 伤害加成系数。不变则1.0。
     */
    protected abstract double judge(Entity target);

    @Override
    public final void handle(PreDamageEvent event) {
        double coefficient = judge(event.getTarget());
        if (coefficient != 1.0) {
            log.info(Msg.trigger(event.getSelf(), this));
            event.multiplyCoefficient(coefficient);
        }
    }
}