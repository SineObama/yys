package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuffProperty;

public class FlagDamage extends BaseComposition {
    public FlagDamage() {
        super(IBuffProperty::getFlagDamage);
    }
}
