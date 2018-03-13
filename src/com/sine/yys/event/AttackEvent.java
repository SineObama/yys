package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 攻击目标事件，不需要造成伤害，在造成伤害前触发，多段攻击也算。
 * 用于附加效果。
 */
public class AttackEvent extends BaseVectorEvent {
    public AttackEvent(Entity entity, Entity target) {
        super(entity, target);
    }
}
