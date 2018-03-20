package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class DefPct extends BaseComposition {
    public DefPct() {
        super(IBuffProperty::getDefPct);
    }
}
