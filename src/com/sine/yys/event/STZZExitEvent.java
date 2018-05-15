package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 霜天之织退出事件，在御魂效果后触发。
 */
public class STZZExitEvent extends BaseVectorEvent {
    public STZZExitEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
