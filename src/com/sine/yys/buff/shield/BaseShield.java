package com.sine.yys.buff.shield;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 盾通用逻辑。
 */
public abstract class BaseShield extends BaseIBuff implements Shield {
    private int value;

    BaseShield(int value, int last, String name, Entity src) {
        super(last, name, src);
        this.value = value;
    }

    @Override
    public final int getValue() {
        return value;
    }

    @Override
    public final int doDamage(int damage) {
        value -= damage;
        if (value > 0)
            return -1;
        return -value;
    }

    @Override
    public final int compareTo(IBuff o) {
        if (getLast() == o.getLast())
            return getValue() - ((Shield) o).getValue();
        return super.compareTo(o);
    }
}
