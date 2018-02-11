package com.sine.yys.simulation.model.buff;

public abstract class BaseIBuff implements IBuff {
    private int last;

    public BaseIBuff(int last) {
        this.last = last;
    }

    @Override
    public String getName() {
        return "效果";
    }

    @Override
    public int getLast() {
        return last;
    }

    @Override
    public void setLast(int last) {
        this.last = last;
    }
}
