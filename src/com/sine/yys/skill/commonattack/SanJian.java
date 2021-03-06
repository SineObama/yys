package com.sine.yys.skill.commonattack;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.skill.model.AttackInfoImpl;

/**
 * 姑获鸟-伞剑。
 */
public class SanJian extends BaseCommonAttack {
    @Override
    public String getName() {
        return "伞剑";
    }

    @Override
    public double getCoefficient() {
        return 0.8 * 1.2;
    }

    public double getIgnoreDefendPct() {
        return 0.2;
    }

    @Override
    public AttackInfo getAttack() {
        return new AttackInfoImpl(getCoefficient(), getIgnoreDefendPct(), 0);
    }
}
