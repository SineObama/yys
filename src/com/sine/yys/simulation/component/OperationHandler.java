package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.operation.Operation;

/**
 * 式神行动处理人。
 * 可通过人工指定（手动）或者式神AI来实现。
 */
public interface OperationHandler {
    Operation handle(Entity entity, Camp own, TargetResolver resolver);
}
