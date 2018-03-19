package com.sine.yys.buff.debuff.control;

import com.sine.yys.buff.BaseCommonIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.Entity;

/**
 * 混乱。
 */
public class HunLuan extends BaseCommonIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan(Entity src) {
        super(1, "混乱", src);
    }
}
