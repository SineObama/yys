package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

import java.util.Collection;

public class BeDamage implements Composition {
    @Override
    public double calc(Collection<IBuff> list) {
        double result = 1;
        for (IBuff buff : list) {
            result *= 1 + buff.getBeDamage();
        }
        return result - 1;
    }
}
