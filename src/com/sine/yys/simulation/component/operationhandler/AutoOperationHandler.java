package com.sine.yys.simulation.component.operationhandler;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.operation.SimpleOperation;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.util.RandUtil;

import java.util.List;
import java.util.Map;

/**
 * 基础AI，使用最大耗火技能，
 */
public class AutoOperationHandler implements OperationHandler {
    @Override
    public Operation handle(Camp own, Map<ActiveSkill, List<? extends Target>> map) {
        int max = 0;
        ActiveSkill use = null;
        for (ActiveSkill activeSkill : map.keySet()) {
            if (activeSkill.getCD() == 0) {
                if (activeSkill.getFire() <= own.getFire() && max <= activeSkill.getFire()) {
                    max = activeSkill.getFire();
                    use = activeSkill;
                }
            }
        }
        if (use == null)
            return null;
        final List<? extends Target> targets = map.get(use);
        return new SimpleOperation(RandUtil.choose(targets), use);
    }
}
