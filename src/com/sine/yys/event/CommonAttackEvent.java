package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 己方式神普攻事件。
 * 保存在阵营中。
 * 用于触发协战。
 */
public class CommonAttackEvent extends BaseVectorEvent implements Event {
    public CommonAttackEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
