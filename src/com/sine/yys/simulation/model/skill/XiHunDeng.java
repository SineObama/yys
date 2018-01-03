package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.ContextAndRunner;

/**
 * 青行灯-吸魂灯。
 */
public class XiHunDeng extends SimpleGroupAttack {
    @Override
    public int getFire() {
        return 3;
    }

    @Override
    public String getName() {
        return "吸魂灯";
    }

    @Override
    public double getCoefficient() {
        return 1.07;
    }

    @Override
    public int getTimes() {
        return 1;
    }

    /**
     * 吸火概率
     */
    public double getPct() {
        return 0.3;
    }

    @Override
    public void apply(ContextAndRunner context) {
        final int grabTimes = context.getEnemy().getAllShikigami().size();
        super.apply(context);
        context.randomGrab(getPct(), grabTimes);
    }
}
