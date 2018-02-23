package com.sine.yys.simulation.component.buff.debuff;

import com.sine.yys.simulation.component.buff.BaseIBuff;
import com.sine.yys.simulation.component.buff.DispellableDebuff;

/**
 * 混乱。
 */
public class HunLuan extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan() {
        super(1, "混乱");
    }
}
