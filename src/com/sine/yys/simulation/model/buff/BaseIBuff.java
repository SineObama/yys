package com.sine.yys.simulation.model.buff;

/**
 * buff通用逻辑。
 * 保存buff名字、持续回合数，提供每回合调用接口step()。
 * 默认所有数值属性为0。
 */
public abstract class BaseIBuff implements IBuff {
    private final String name;
    private int last;

    /**
     * @param last 持续回合数。必须为正。
     */
    public BaseIBuff(int last, String name) {
        this.name = name;
        if (last <= 0)
            throw new RuntimeException("buff持续回合不为正。");
        this.last = last;
    }

    public final int getLast() {
        return last;
    }

    @Override
    public final int step() {
        if (last > 0)
            last -= 1;
        afterStep();
        return last;
    }

    protected void afterStep() {
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public double getAtkPct() {
        return 0;
    }

    @Override
    public double getDefPct() {
        return 0;
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public double getCritical() {
        return 0;
    }

    @Override
    public double getCriticalDamage() {
        return 0;
    }

    @Override
    public double getEffectHit() {
        return 0;
    }

    @Override
    public double getEffectDef() {
        return 0;
    }
}
