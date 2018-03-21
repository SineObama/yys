package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

import java.util.Collection;

/**
 * 默认操作，直接加减。
 */
public abstract class BaseComposition implements Composition {
    final Getter getter;

    BaseComposition(Getter getter) {
        this.getter = getter;
    }

    public double calc(Collection<IBuff> list) {
        double result = 0;
        for (IBuff buff : list) {
            result += getter.get(buff);
        }
        return result;
    }
}
