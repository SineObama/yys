package com.sine.yys.simulation.model.shield;

import com.sine.yys.simulation.model.buff.BaseIBuff;

public abstract class BaseShield extends BaseIBuff implements Shield {
    private int value;

    public BaseShield(int value, int last) {
        super(last);
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int doDamage(int damage) {
        value -= damage;
        if (value > 0)
            return 0;
        return -value;
    }
}
