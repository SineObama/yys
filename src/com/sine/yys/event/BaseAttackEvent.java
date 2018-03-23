package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 事件拓展基类，包含2个实体、攻击类型。
 */
public abstract class BaseAttackEvent extends BaseVectorEvent {
    private final AttackType type;

    BaseAttackEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target);
        this.type = type;
    }

    public AttackType getType() {
        return type;
    }
}
