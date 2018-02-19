package com.sine.yys.simulation.component.operation;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.skill.ActiveSkill;
import com.sine.yys.simulation.util.RandUtil;

import java.util.List;
import java.util.Map;

/**
 * 基础AI，使用最大耗火技能。
 */
public class AutoOperationHandler implements OperationHandler {
    @Override
    public Operation handle(Entity self, Map<ActiveSkill, List<? extends Entity>> map) {
        int max = 0;
        ActiveSkill use = null;
        for (ActiveSkill activeSkill : map.keySet()) {
            if (activeSkill.getCD() == 0) {
                if (max <= activeSkill.getFire()) {
                    max = activeSkill.getFire();
                    use = activeSkill;
                }
            }
        }
        if (use == null)
            return new Operation(null, null);
        final List<? extends Entity> targets = map.get(use);
        return new Operation(RandUtil.choose(targets), use);
    }
}
