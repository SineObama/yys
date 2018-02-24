package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseCommonIBuff;

/**
 * 混乱。
 */
public class HunLuan extends BaseCommonIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan() {
        super(1, "混乱");
    }
}
