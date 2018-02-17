package com.sine.yys.simulation.model.buff;

/**
 * 单数值buff。
 * 包括攻击、防御、速度、命中抵抗等等。
 * 具体语义由子类给出。
 */
public abstract class NumIBuff extends BaseIBuff implements IBuff {
    protected final double value;

    /**
     * @param last 持续回合数。必须为正。
     */
    public NumIBuff(int last, String name, double value) {
        super(last, name);
        this.value = value;
    }
}
