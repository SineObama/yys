package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 进入战场或复活时，表示出现在战场上。
 * 目前用于添加事件监听器。
 */
public class EnterEvent extends BaseEntityEvent {
    public EnterEvent(Entity entity) {
        super(entity);
    }
}
