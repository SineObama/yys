package com.sine.yys.simulation.model.effect;

/**
 * 反弹伤害。包括反弹概率，反弹百分比。
 */
public interface ReBound extends Effect {
    double getPct();

    double getRatio();
}
