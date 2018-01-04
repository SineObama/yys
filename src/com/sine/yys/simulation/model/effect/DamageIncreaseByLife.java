package com.sine.yys.simulation.model.effect;

import com.sine.yys.simulation.model.entity.Entity;

public class DamageIncreaseByLife implements DamageChange {
    private final double begin;
    private final double end;
    private final double coefficient;

    public double getCoefficient() {
        return coefficient;
    }

    public DamageIncreaseByLife(double begin, double end, double coefficient) {
        this.begin = begin;
        this.end = end;
        this.coefficient = coefficient;
    }

    @Override
    public boolean judge(Entity target) {
        return target.getLifePct() >= begin && target.getLifePct() <= end;
    }
}
