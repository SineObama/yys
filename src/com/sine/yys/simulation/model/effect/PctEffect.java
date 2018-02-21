package com.sine.yys.simulation.model.effect;

/**
 * 概率生效的效果，给目标添加debuff，受效果命中抵抗影响。
 */
public interface PctEffect extends Effect {
    double getPct();
}
