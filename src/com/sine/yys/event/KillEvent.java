package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 击杀事件。目标死亡就触发。
 * <p>
 * 阴摩罗规则：
 * * 薙魂者死亡不算；
 * * 涓流间接击杀算；
 * * 毒伤算；
 */
public class KillEvent extends BaseAttackEvent {
    public KillEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
