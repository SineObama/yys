package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class Cure extends BaseComposition {
    public Cure() {
        super(IBuffProperty::getCure);
    }
}
