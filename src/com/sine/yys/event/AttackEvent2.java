package com.sine.yys.event;

import com.sine.yys.inter.Entity;

/**
 * 攻击目标事件。
 * <p>
 * 不需要造成伤害，在造成伤害前触发，多段攻击也算。
 * 在御魂效果后触发。
 * 用于：
 * 1. 烈焰；
 */
public class AttackEvent2 extends BaseVectorEvent {
    public AttackEvent2(Entity entity, Entity target) {
        super(entity, target);
    }
}
