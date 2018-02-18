package com.sine.yys.simulation.info;

public class AttackInfoImpl implements AttackInfo {
    private final double coefficient;
    private final double ignoreDefensePct;
    private final int IgnoreDefense;

    public AttackInfoImpl(double coefficient) {
        this.coefficient = coefficient;
        this.ignoreDefensePct = 0;
        IgnoreDefense = 0;
    }

    public AttackInfoImpl(double coefficient, double ignoreDefensePct, int ignoreDefense) {
        this.coefficient = coefficient;
        this.ignoreDefensePct = ignoreDefensePct;
        IgnoreDefense = ignoreDefense;
    }

    @Override
    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public double getIgnoreDefensePct() {
        return ignoreDefensePct;
    }

    @Override
    public int getIgnoreDefense() {
        return IgnoreDefense;
    }
}
