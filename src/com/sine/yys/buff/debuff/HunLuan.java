package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.buff.ControlBuff;

/**
 * 混乱。
 */
public class HunLuan extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan() {
        super(1, "混乱");
    }
}
