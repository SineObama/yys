package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 己方式神普攻事件。
 * <p>
 * 用途：触发协战。
 */
public class CommonAttackEvent extends BaseVectorEvent {
    public CommonAttackEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
