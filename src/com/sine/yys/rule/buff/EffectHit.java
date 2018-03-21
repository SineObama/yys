package com.sine.yys.rule.buff;

import com.sine.yys.inter.base.IBuffProperty;

public class EffectHit extends BaseComposition {
    public EffectHit() {
        super(IBuffProperty::getEffectHit);
    }
}
