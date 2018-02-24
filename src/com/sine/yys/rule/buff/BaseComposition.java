package com.sine.yys.rule.buff;

import com.sine.yys.buff.IBuff;
import com.sine.yys.simulation.component.Container;

import java.util.Set;

/**
 * 默认操作，直接加减。
 */
public abstract class BaseComposition implements Composition {
    /**
     * 用于保存中间数值。子类对此变量进行修改。
     */
    protected double product;

    @Override
    public final double calc(Set<Container<IBuff>>... lists) {
        for (Set<Container<IBuff>> list : lists) {
            for (Container<IBuff> container : list) {
                and(container.getObj());
            }
        }
        return product;
    }

    /**
     * 子类要重写的函数，对特定的属性进行一次叠加。
     */
    abstract protected void and(IBuff iBuff);
}
