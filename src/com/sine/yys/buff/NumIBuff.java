package com.sine.yys.buff;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 单数值buff通用逻辑。
 * <p>
 * 由子类定义属性类型（重写{@linkplain com.sine.yys.inter.base.IBuffProperty}其中一个接口）。
 */
public abstract class NumIBuff extends BaseIBuff {
    protected final double value;

    public NumIBuff(int last, String name, double value, Entity src) {
        super(last, name, src);
        this.value = value;
    }

    @Override
    public final int compareTo(IBuff o) {
        final double ovalue = ((NumIBuff) o).value;
        if (value == ovalue)
            return super.compareTo(o);
        return (int) (value - ovalue);
    }
}
