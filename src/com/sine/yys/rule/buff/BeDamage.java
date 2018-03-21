package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;
import com.sine.yys.inter.base.IBuffProperty;

import java.util.Collection;

public class BeDamage extends BaseComposition {
    public BeDamage() {
        super(IBuffProperty::getBeDamage);
    }

    @Override
    public double calc(Collection<IBuff> list) {
        double result = 1;
        for (IBuff buff : list) {
            result *= 1 + getter.get(buff);
        }
        return result - 1;
    }
}
