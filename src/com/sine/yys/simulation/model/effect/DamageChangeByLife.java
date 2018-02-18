package com.sine.yys.simulation.model.effect;

import com.sine.yys.simulation.model.Entity;

/**
 * 若目标生命***则伤害增/减***
 */
public class DamageChangeByLife extends DamageChange implements Effect {
    private final double begin;
    private final double end;
    private final double coefficient;

    public DamageChangeByLife(double begin, double end, double coefficient, String name) {
        super(name);
        this.begin = begin;
        this.end = end;
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public double judge(Entity target) {
        if (target.getLife() >= begin && target.getLife() <= end)
            return getCoefficient();
        else
            return 1.0;
    }
}
