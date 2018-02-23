package com.sine.yys.simulation.component.model.shishen.skill.operation;

import com.sine.yys.simulation.component.model.Entity;
import com.sine.yys.simulation.component.model.shishen.skill.ActiveSkill;

import java.util.List;
import java.util.Map;

/**
 * 式神行动处理器，决定式神的技能使用。
 * 可人工指定（手动）或者实现式神AI。
 */
public interface OperationHandler {
    /**
     * @param self 当前行动的式神。
     * @param map  技能到可选目标的映射。
     */
    Operation handle(Entity self, Map<ActiveSkill, List<? extends Entity>> map);
}
