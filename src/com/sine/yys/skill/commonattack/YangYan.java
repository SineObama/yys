package com.sine.yys.skill.commonattack;

/**
 * 日和坊-阳炎。
 */
public class YangYan extends BaseCommonAttack {
    @Override
    public String getName() {
        return "阳炎";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.25;
    }
}
