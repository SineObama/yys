package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class Speed extends BaseComposition {
    public Speed() {
        super(IBuffProperty::getSpeed);
    }
}
