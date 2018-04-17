package com.sine.yys.skill.model;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.util.RandUtil;

import java.util.Collection;
import java.util.Collections;

public class AttackInfoImpl implements AttackInfo {
    public static double _defaultWaveRadius = 0.01;
    private final double coefficient;
    private final double ignoreDefensePct;
    private final int IgnoreDefense;
    private final double waveRadius;
    private final Collection<DebuffEffect> debuffEffects;

    public AttackInfoImpl(Collection<DebuffEffect> debuffEffects, double coefficient) {
        this.debuffEffects = debuffEffects;
        this.coefficient = coefficient;
        this.ignoreDefensePct = 0;
        IgnoreDefense = 0;
        waveRadius = _defaultWaveRadius;
    }

    public AttackInfoImpl(Collection<DebuffEffect> debuffEffects, double coefficient, double waveRadius) {
        this.debuffEffects = debuffEffects;
        this.coefficient = coefficient;
        this.ignoreDefensePct = 0;
        IgnoreDefense = 0;
        this.waveRadius = waveRadius;
    }

    public AttackInfoImpl(Collection<DebuffEffect> debuffEffects, double coefficient, double ignoreDefensePct, int ignoreDefense) {
        this.debuffEffects = debuffEffects;
        this.coefficient = coefficient;
        this.ignoreDefensePct = ignoreDefensePct;
        IgnoreDefense = ignoreDefense;
        waveRadius = _defaultWaveRadius;
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

    @Override
    public Collection<DebuffEffect> getDebuffEffects() {
        return debuffEffects != null ? debuffEffects : Collections.EMPTY_SET;
    }
}
