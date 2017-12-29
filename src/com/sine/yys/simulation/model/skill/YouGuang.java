package com.sine.yys.simulation.model.skill;

/**
 * 青行灯-幽光。
 */
public class YouGuang extends CommonAttack {
    @Override
    public String getName() {
        return "幽光";
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
        return 1.0;
    }
}
