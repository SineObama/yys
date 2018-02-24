package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

public class DefPct extends BaseComposition {
    @Override
    protected void and(IBuff iBuff) {
        product += iBuff.getDefPct();
    }
}
