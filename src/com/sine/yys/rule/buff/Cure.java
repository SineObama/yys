package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class Cure extends BaseComposition {
    public Cure() {
        super(IBuffProperty::getCure);
    }

    public double calc(double... d) {
        double sum = 0.0;
        for (double v : d) {
            sum += v;
        }
        return sum;
    }
}
