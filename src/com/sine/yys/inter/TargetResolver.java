package com.sine.yys.inter;

import java.util.List;

/**
 * 技能的可选目标查找器。由技能定义、实现此类。
 *
 * @see ActiveSkill
 */
public interface TargetResolver {
    /**
     * @param own  控制器。
     * @param self 当前行动的式神。
     * @return 空列表：不需要选择目标的情况返回。null：技能无目标可选。
     */
    List<? extends Entity> resolve(Camp own, Entity self);
}
