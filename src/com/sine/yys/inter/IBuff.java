package com.sine.yys.inter;

import com.sine.yys.inter.base.IBuffProperty;
import com.sine.yys.inter.base.Named;

import java.util.Collection;

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

    /**
     * 式神身上含有同类buff的上限。
     * 一般情况为1，毒伤一般为3。
     */
    default int maxCount() {
        return 1;
    }

    /**
     * 是否用本buff替换参数中的某个原有的同类buff。
     *
     * @param buffs 原有buff，非空，和本buff类型相同。
     * @return 要替换的buff，null表示不替换。
     */
    IBuff replace(Collection<IBuff> buffs);

    /**
     * @return 式神死亡时移除。
     */
    default boolean removeOnDie() {
        return true;
    }
}
