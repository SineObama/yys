package com.sine.yys.simulation.component.model.buff.debuff;

import com.sine.yys.simulation.component.model.buff.BaseIBuff;
import com.sine.yys.simulation.component.model.buff.DispellableDebuff;

/**
 * 混乱。
 */
public class HunLuan extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan() {
        super(1, "混乱");
    }
}
