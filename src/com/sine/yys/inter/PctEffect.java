package com.sine.yys.inter;

/**
 * 概率生效的效果，给目标添加debuff，受效果命中抵抗影响。
 */
public interface PctEffect extends Named {
    double getPct();
}
