package com.sine.yys.simulation.model.shield;

public abstract class BaseShield implements Shield {
    private int value;
    private int last;

    public BaseShield(int value, int last) {
        this.value = value;
        this.last = last;
    }

    @Override
    public String getName() {
        return "盾";
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getLast() {
        return last;
    }

    @Override
    public int doDamage(int damage) {
        value -= damage;
        if (value > 0)
            return 0;
        return -value;
    }
}
