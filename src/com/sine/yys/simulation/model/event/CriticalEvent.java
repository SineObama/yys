package com.sine.yys.simulation.model.event;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 暴击事件。必须是造成伤害的暴击，不算护盾。
 */
public class CriticalEvent extends BaseVectorEvent implements Event {
    public CriticalEvent(Entity self, Entity target) {
        super(self, target);
    }
}
