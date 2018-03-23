package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 回合前事件。
 * <p>
 * 用途：
 * 1. 赤团华、龙首之玉、归鸟；
 */
public class BeforeRoundEvent extends BaseEntityEvent {
    public BeforeRoundEvent(Entity entity) {
        super(entity);
    }
}
