package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class Critical extends BaseComposition {
    public Critical() {
        super(IBuffProperty::getCritical);
    }
}
