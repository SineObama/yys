package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 事件拓展基类，包含2个实体、攻击类型。
 * （定义此为指向性信息。）
 */
public abstract class BaseAttackEvent extends BaseVectorEvent implements Event {
    private final AttackType type;

    public BaseAttackEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target);
        this.type = type;
    }

    public AttackType getType() {
        return type;
    }
}
