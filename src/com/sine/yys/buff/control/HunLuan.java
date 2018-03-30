package com.sine.yys.buff.control;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.inter.Entity;

/**
 * 混乱。
 */
public class HunLuan extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan(Entity src) {
        super(1, "混乱", src);
    }
}
