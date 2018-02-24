package com.sine.yys.buff.buff;

import com.sine.yys.buff.NumIBuff;

/**
 * 攻击增减buff（按百分比）。
 */
public class AttackIBuff extends NumIBuff implements DispellableBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param name   名称。
     * @param atkPct 防御百分比。
     */
    public AttackIBuff(int last, String name, double atkPct) {
        super(last, name, atkPct);
    }

    @Override
    public double getAtkPct() {
        return value;
    }
}
