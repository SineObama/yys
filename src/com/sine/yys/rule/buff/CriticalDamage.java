package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

public class CriticalDamage extends BaseComposition {
    @Override
    protected void and(IBuff iBuff) {
        product += iBuff.getCriticalDamage();
    }
}
