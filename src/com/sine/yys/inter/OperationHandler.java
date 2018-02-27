package com.sine.yys.inter;

import java.util.List;
import java.util.Map;

/**
 * 式神行动处理器，决定式神的技能使用、目标的式神。
 * 可人工指定（手动）或者实现式神AI。
 */
public interface OperationHandler {
    /**
     * @param self 当前行动的式神。
     * @param map  可用的技能，以及其对应的可选目标。
     */
    Operation handle(Entity self, Map<ActiveSkill, List<? extends Entity>> map);
}
