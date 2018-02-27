package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

public class Speed extends BaseComposition {
    @Override
    protected void and(IBuff iBuff) {
        product += iBuff.getSpeed();
    }
}
