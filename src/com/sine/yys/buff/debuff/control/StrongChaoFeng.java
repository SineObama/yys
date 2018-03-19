package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.inter.Entity;

/**
 * 强嘲讽。
 * 无法驱散。
 */
public class StrongChaoFeng extends BaseCommonIBuff implements ChaoFeng {
    public StrongChaoFeng(Entity src) {
        super(1, "强嘲讽", src);
    }
}
