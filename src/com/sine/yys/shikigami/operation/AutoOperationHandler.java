package com.sine.yys.shikigami.operation;

import com.sine.yys.inter.*;
import com.sine.yys.util.RandUtil;

import java.util.List;
import java.util.Map;

/**
 * 基础AI，使用最大耗火技能，在普攻时有补刀机制。
 */
public class AutoOperationHandler implements OperationHandler {
    @Override
    public Operation handle(Entity self, Camp own, Map<ActiveSkill, List<? extends Entity>> map) {
        int max = 0;
        ActiveSkill use = null;
        for (ActiveSkill activeSkill : map.keySet()) {
            if (max <= activeSkill.getFire()) {
                max = activeSkill.getFire();
                use = activeSkill;
            }
        }
        if (use == null)
            return new OperationImpl(null, null);
        final List<? extends Entity> targets = map.get(use);
        Entity target = null;
        if (use instanceof CommonAttack) {
            for (Entity entity : targets) {
                if (entity.getLife() < 0.1)
                    target = entity;
            }
        }
        if (target == null)
            target = RandUtil.choose(targets);
        return new OperationImpl(target, use);
    }
}
