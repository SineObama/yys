package com.sine.yys.simulation.component.core.model.buff.debuff;

import com.sine.yys.simulation.component.core.model.buff.BaseIBuff;
import com.sine.yys.simulation.component.core.model.buff.DispellableDebuff;

/**
 * 混乱。
 */
public class HunLuan extends BaseIBuff implements DispellableDebuff, ControlBuff {
    public HunLuan() {
        super(1, "混乱");
    }
}
