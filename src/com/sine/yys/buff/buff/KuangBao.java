package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.UniqueIBuff;

/**
 * 般若的狂暴状态，隐藏buff。
 */
public class KuangBao extends BaseIBuff implements UniqueIBuff, Buff {
    public KuangBao() {
        super(2 + 1, "狂暴状态");
    }
}
