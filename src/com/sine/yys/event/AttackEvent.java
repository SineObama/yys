package com.sine.yys.event;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;

/**
 * 攻击目标事件，在造成伤害前触发，多段攻击也算。
 * 用于附加效果（不需要造成伤害也能生效）。
 */
public class AttackEvent extends BaseVectorEvent {
    public AttackEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
