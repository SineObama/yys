package com.sine.yys.skill;

import com.sine.yys.info.AttackInfo;
import com.sine.yys.info.AttackInfoImpl;

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
