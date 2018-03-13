package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuffProperty;

public class DamageUp extends BaseComposition {
    public DamageUp() {
        super(IBuffProperty::getDamageUp);
    }
}
