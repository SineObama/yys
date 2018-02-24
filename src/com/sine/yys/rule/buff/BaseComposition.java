package com.sine.yys.rule.buff;

import com.sine.yys.buff.IBuff;

import java.util.Collection;

/**
 * 默认操作，直接加减。
 */
public abstract class BaseComposition implements Composition {
    /**
     * 用于保存中间数值。子类对此变量进行修改。
     */
    protected double product = 0;

    @Override
    public final double calc(Collection<IBuff>... lists) {
        for (Collection<IBuff> list : lists) {
            for (IBuff buff : list) {
                and(buff);
            }
        }
        return product;
    }

    /**
     * 子类要重写的函数，对特定的属性进行一次叠加。
     */
    abstract protected void and(IBuff iBuff);
}
