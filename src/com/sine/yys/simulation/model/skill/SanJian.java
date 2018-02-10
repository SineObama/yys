package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.Attack;
import com.sine.yys.simulation.model.AttackImpl;

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
        return 0.8;
    }

    public double getIgnoreDefendPct() {
        return 0.2;
    }

    @Override
    public Attack getAttack() {
        return new AttackImpl(getCoefficient(), getIgnoreDefendPct(), 0);
    }
}
