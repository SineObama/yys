package com.sine.yys.skill.model;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.util.RandUtil;

public class AttackInfoImpl implements AttackInfo {
    private final static double defaultWaveRadius = 0.01;
    private final double coefficient;
    private final double ignoreDefensePct;
    private final int IgnoreDefense;
    private final double waveRadius;

    public AttackInfoImpl(double coefficient) {
        this.coefficient = coefficient;
        this.ignoreDefensePct = 0;
        IgnoreDefense = 0;
        waveRadius = defaultWaveRadius;
    }

    public AttackInfoImpl(double coefficient, double waveRadius) {
        this.coefficient = coefficient;
        this.ignoreDefensePct = 0;
        IgnoreDefense = 0;
        this.waveRadius = waveRadius;
    }

    public AttackInfoImpl(double coefficient, double ignoreDefensePct, int ignoreDefense) {
        this.coefficient = coefficient;
        this.ignoreDefensePct = ignoreDefensePct;
        IgnoreDefense = ignoreDefense;
        waveRadius = defaultWaveRadius;
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

    @Override
    public double randomFloat() {
        return RandUtil.doubles(1 - waveRadius, 1 + waveRadius);
    }
}
