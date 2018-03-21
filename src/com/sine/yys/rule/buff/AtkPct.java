package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class AtkPct extends BaseComposition {
    public AtkPct() {
        super(IBuffProperty::getAtkPct);
    }
}
