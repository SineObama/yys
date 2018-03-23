package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 回合后事件。
 * <p>
 * 在回合流程中触发。
 */
public class AfterRoundEvent extends BaseEntityEvent {
    public AfterRoundEvent(Entity entity) {
        super(entity);
    }
}
