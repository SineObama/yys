package com.sine.yys.simulation.component.operationhandler;

import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.operation.Operation;
import com.sine.yys.simulation.model.skill.ActiveSkill;

import java.util.List;
import java.util.Map;

/**
 * 式神行动处理人。
 * 可通过人工指定（手动）或者式神AI来实现。
 */
public interface OperationHandler {
    Operation handle(Entity entity, Camp own, Map<ActiveSkill, List<Entity>> map);
}
