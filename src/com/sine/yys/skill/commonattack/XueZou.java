package com.sine.yys.skill.commonattack;

/**
 * 雪童子-雪走。
 */
public class XueZou extends BaseCommonAttack {
    @Override
    public String getName() {
        return "雪走";
    }

    @Override
    public double getCoefficient() {
        return 0.8 * 1.25;
    }
}
