package com.sine.yys.simulation.component.operationhandler;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.Target;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.operation.SimpleOperation;
import com.sine.yys.simulation.model.skill.ActiveSkill;
import com.sine.yys.simulation.model.skill.Skill;

import java.util.List;
import java.util.Map;

public class AutoOperationHandler implements OperationHandler {
    @Override
    public Operation handle(Entity entity, Camp own, Map<ActiveSkill, List<? extends Target>> map) {
        final List<Skill> skills = entity.getSkills();
        int max = 0;
        ActiveSkill use = null;
        for (Skill skill : skills) {
            if (skill instanceof ActiveSkill) {
                ActiveSkill activeSkill = (ActiveSkill) skill;
                if (activeSkill.getFire() <= own.getFire() && max <= activeSkill.getFire()) {
                    max = activeSkill.getFire();
                    use = activeSkill;
                }
            }
        }
        if (use == null)
            return null;
        final List<? extends Target> targets = map.get(use);
        if (targets.isEmpty())
            return null;
        return new SimpleOperation(entity, targets.get(0), use);
    }
}
