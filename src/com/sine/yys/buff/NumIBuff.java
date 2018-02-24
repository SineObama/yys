package com.sine.yys.buff;

import com.sine.yys.inter.IBuff;

/**
 * 单数值buff。
 * 包括攻击、防御、速度、命中抵抗等等。
 * 具体语义由子类给出。
 */
public abstract class NumIBuff extends BaseCommonIBuff implements IBuff {
    protected final double value;

    /**
     * @param last 持续回合数。必须为正。
     */
    public NumIBuff(int last, String name, double value) {
        super(last, name);
        this.value = value;
    }

    @Override
    public IBuff combineWith(IBuff o) {
        final double ovalue = ((NumIBuff) o).value;
        if (value == ovalue)
            return super.combineWith(o);
        return value > ovalue ? this : o;
    }
}
