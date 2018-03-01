package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

public class Cure extends BaseComposition {
    @Override
    protected void and(IBuff iBuff) {
        product += iBuff.getCure();
    }
}
