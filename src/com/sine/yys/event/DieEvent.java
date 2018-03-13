package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 死亡事件。
 */
public class DieEvent extends BaseEntityEvent {
    public DieEvent(Entity entity) {
        super(entity);
    }
}
