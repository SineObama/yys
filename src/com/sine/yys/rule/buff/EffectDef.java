package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuffProperty;

public class EffectDef extends BaseComposition {
    public EffectDef() {
        super(IBuffProperty::getEffectDef);
    }
}
