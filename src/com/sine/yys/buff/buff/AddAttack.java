package com.sine.yys.buff.buff;

import com.sine.yys.buff.AttackIBuff;
import com.sine.yys.inter.Entity;

public abstract class AddAttack extends AttackIBuff implements DispellableBuff {
    protected AddAttack(int last, String prefix, double atkPct, Entity src) {
        super(last, prefix + "-增加", atkPct, src);
    }
}
