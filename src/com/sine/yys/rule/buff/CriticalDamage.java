package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuffProperty;

public class CriticalDamage extends BaseComposition {
    public CriticalDamage() {
        super(IBuffProperty::getCriticalDamage);
    }
}
