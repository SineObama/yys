package com.sine.yys.inter;

import java.util.List;

/**
 * 技能的可选目标查找器。
 * <p>
 * 由技能定义、实现此类。
 *
 * @see ActiveSkill
 */
public interface TargetResolver {
    /**
     * @param own  自身阵营
     * @param self 当前行动的式神。
     * @return 不需要选择目标则返回空列表。技能无目标可选（无法使用技能）则返回null。
     */
    List<? extends Entity> resolve(Camp own, Entity self);
}
