package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;
import com.sine.yys.inter.base.IBuffProperty;

import java.util.Collection;

/**
 * 对buff属性的叠加。
 * <p>
 * 比如2个攻击加成30%叠加就是60%。
 *
 * @see IBuffProperty
 */
public interface Composition {
    /**
     * 计算集合中所有buff某一属性的总和。
     *
     * @return 最终加成数值。
     */
    double calc(Collection<IBuff> list);
}
