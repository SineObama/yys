package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 攻击目标事件。
 * <p>
 * 不需要造成伤害，在造成伤害前触发，多段攻击也算。
 * 用于：
 * 1. 附加效果（不需要造成伤害）；
 * 2. 伤害系数变动；
 */
public class AttackEvent extends BaseAttackEvent {
    public AttackEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
