package com.sine.yys.simulation.model.buff;

import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.shield.Shield;

import java.util.List;

/**
 * 目前包括对盾的添加操作：检查重复，设置优先级。
 */
public interface BuffController {
    /**
     * 不同的盾不会互相排斥。
     * 相同的盾如果容量更大会进行替换。
     */
    void addShield(Shield shield);

    /**
     * 按照消耗顺序返回。
     */
    List<Shield> getShields();

    /**
     * 行动后执行。
     * 给效果回合数减1，减到0则移除效果。
     */
    void afterAction(Entity self);
}
