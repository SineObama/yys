package com.sine.yys.simulation.component.core.model.buff.buff;

import com.sine.yys.simulation.component.core.model.buff.BaseIBuff;
import com.sine.yys.simulation.component.core.model.buff.Buff;
import com.sine.yys.simulation.component.core.model.buff.UniqueIBuff;

/**
 * 般若的狂暴状态，隐藏buff。
 */
public class KuangBao extends BaseIBuff implements UniqueIBuff, Buff {
    public KuangBao() {
        super(2 + 1, "狂暴状态");
    }
}
