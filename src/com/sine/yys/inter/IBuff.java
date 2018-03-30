package com.sine.yys.inter;

import com.sine.yys.inter.base.IBuffProperty;
import com.sine.yys.inter.base.Named;

/**
 * 式神头上显示的buff。提供属性加成、护盾、控制等。
 * <p>
 * 一般可分为增益和减益两大类。
 * <p>
 * 统一实现{@linkplain IBuffProperty}以便统计数值。
 *
 * @see BuffController
 */
public interface IBuff extends IBuffProperty, Named, Comparable<IBuff> {
    /**
     * @param count 增加持续回合的数量。
     */
    void addLast(int count);

    /**
     * @return 持续回合数。0表示buff应当被移除。永久buff以（接近）整数最大值表示。
     */
    int getLast();

    /**
     * @return 来源式神。
     */
    Entity getSrc();
}
