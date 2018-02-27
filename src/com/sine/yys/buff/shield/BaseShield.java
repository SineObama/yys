package com.sine.yys.buff.shield;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 盾通用逻辑。
 * 包括盾的数值。
 */
public abstract class BaseShield extends BaseCommonIBuff implements Shield {
    private int value;

    public BaseShield(int value, int last, String name, Entity src) {
        super(last, name, src);
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

    @Override
    public IBuff combineWith(IBuff o) {
        if (getLast() == o.getLast())
            return getValue() > ((Shield) o).getValue() ? this : o;
        return super.combineWith(o);
    }
}
