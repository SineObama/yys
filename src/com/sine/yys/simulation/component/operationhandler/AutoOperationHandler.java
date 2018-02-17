package com.sine.yys.simulation.component.operationhandler;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.operation.SimpleOperation;
import com.sine.yys.simulation.model.skill.ActiveSkill;
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
            return new SimpleOperation(null, null);
        final List<? extends Entity> targets = map.get(use);
        return new SimpleOperation(RandUtil.choose(targets), use);
    }
}
