package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 行动后事件。
 * 在行动流程中触发。
 */
public class AfterActionEvent extends BaseEntityEvent implements Event {
    public AfterActionEvent(Entity entity) {
        super(entity);
    }
}
