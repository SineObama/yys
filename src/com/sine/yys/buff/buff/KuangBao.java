package com.sine.yys.buff.buff;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Buff;

/**
 * 般若的狂暴状态，隐藏buff。
 */
public class KuangBao extends BaseCommonIBuff implements Buff {
    public KuangBao(int last) {
        super(last, "狂暴状态");
    }
}
