package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuffProperty;

public class DefPct extends BaseComposition {
    public DefPct() {
        super(IBuffProperty::getDefPct);
    }
}
