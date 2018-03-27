package com.sine.yys.event;

import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Entity;

/**
 * 击杀事件。
 * <p>
 * 规则（根据阴摩罗得出）：
 * 1. 薙魂者死亡不算；
 * 2. 涓流间接击杀算；
 * 3. 毒伤算。
 */
public class KillEvent extends BaseAttackEvent {
    public KillEvent(Entity entity, Entity target, AttackType type) {
        super(entity, target, type);
    }
}
