package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuffProperty;

public class AtkPct extends BaseComposition {
    public AtkPct() {
        super(IBuffProperty::getAtkPct);
    }
}
