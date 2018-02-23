package com.sine.yys.skill.commonattack;

import com.sine.yys.skill.commonattack.BaseCommonAttack;

/**
 * 彼岸花-死亡之花。
 */
public class SiWangZhiHua extends BaseCommonAttack {
    @Override
    public String getName() {
        return "死亡之花";
    }

    @Override
    public double getCoefficient() {
        return 1 * 1.25;
    }
}
