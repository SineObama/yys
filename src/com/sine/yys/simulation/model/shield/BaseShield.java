package com.sine.yys.simulation.model.shield;

import com.sine.yys.simulation.model.buff.BaseIBuff;

/**
 * 盾通用逻辑。
 * 包括盾的数值。
 */
public abstract class BaseShield extends BaseIBuff implements Shield {
    private int value;

    public BaseShield(int value, int last, String name) {
        super(last, name);
        this.value = value;
    }

    @Override
    public final int getValue() {
        return value;
    }

    @Override
    public int doDamage(int damage) {
        value -= damage;
        if (value > 0)
            return -1;
        return -value;
    }
}
