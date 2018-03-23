package com.sine.yys.buff.buff;

import com.sine.yys.buff.CriticalDamageIBuff;
import com.sine.yys.inter.Entity;

public abstract class AddCriticalDamage extends CriticalDamageIBuff implements DispellableBuff {
    protected AddCriticalDamage(int last, String prefix, double criticalDamage, Entity src) {
        super(last, prefix + "-增加", criticalDamage, src);
    }
}
