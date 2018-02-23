package com.sine.yys.buff.shield;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.buff.Shield;

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
