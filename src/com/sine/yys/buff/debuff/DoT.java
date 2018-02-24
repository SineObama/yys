package com.sine.yys.buff.debuff;

import com.sine.yys.inter.Entity;

/**
 * 持续伤害。
 */
public interface DoT extends Debuff {
    /**
     * 回合开始时调用。
     *
     * @param entity 当前中毒对象。
     * @return 持续伤害。
     */
    double handle(Entity entity);
}