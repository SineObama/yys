package com.sine.yys.rule.buff;

import com.sine.yys.buff.IBuff;

public class AtkPct extends BaseComposition {
    @Override
    protected void and(IBuff iBuff) {
        product += iBuff.getAtkPct();
    }
}
