package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class CriticalDamage extends BaseComposition {
    public CriticalDamage() {
        super(IBuffProperty::getCriticalDamage);
    }
}
