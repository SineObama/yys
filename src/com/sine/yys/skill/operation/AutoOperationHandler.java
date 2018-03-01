package com.sine.yys.skill.operation;

import com.sine.yys.inter.*;
import com.sine.yys.util.RandUtil;

import java.util.List;
import java.util.Map;

/**
 * 基础AI，使用最大耗火技能。
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
        return new OperationImpl(RandUtil.choose(targets), use);
    }
}
