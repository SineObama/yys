package com.sine.yys.simulation.model.skill;

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
    public String getDetail() {
        return "";
    }

    @Override
    public int getCD() {
        return 0;
    }

    @Override
    public double getCoefficient() {
        return 107;
    }

    @Override
    public int getTimes() {
        return 1;
    }
}
