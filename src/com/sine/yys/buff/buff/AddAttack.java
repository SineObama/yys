package com.sine.yys.buff.buff;

import com.sine.yys.buff.AttackIBuff;
import com.sine.yys.inter.Entity;

public abstract class AddAttack extends AttackIBuff implements DispellableBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param prefix 名称。
     * @param atkPct 防御百分比。
     * @param src    来源式神。
     */
    protected AddAttack(int last, String prefix, double atkPct, Entity src) {
        super(last, prefix + "-增加", atkPct, src);
    }
}
