package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 行动后事件。
 * <p>
 * 在行动流程中触发。
 */
public class AfterActionEvent extends BaseEntityEvent {
    public AfterActionEvent(Entity entity) {
        super(entity);
    }
}
