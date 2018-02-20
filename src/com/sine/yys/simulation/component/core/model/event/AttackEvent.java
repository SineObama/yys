package com.sine.yys.simulation.component.core.model.event;

import com.sine.yys.simulation.component.inter.Entity;

/**
 * 攻击目标事件，多段攻击也算。
 */
public class AttackEvent extends BaseVectorEvent {
    public AttackEvent(Entity self, Entity target) {
        super(self, target);
    }
}
