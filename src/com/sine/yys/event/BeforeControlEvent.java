package com.sine.yys.event;

import com.sine.yys.inter.ControlBuff;

/**
 * 实施控制效果前触发。
 * <p>
 * 用途：
 * 1. 花鸟卷抵消控制效果（冻结、眩晕、睡眠、变形、沉默、嘲讽、混乱、牢笼束缚等的技能和御魂效果）。
 */
public class BeforeControlEvent extends BaseEvent {
    final ControlBuff controlBuff;
    boolean effective = true;

    public BeforeControlEvent(ControlBuff controlBuff) {
        this.controlBuff = controlBuff;
    }

    public ControlBuff getControlBuff() {
        return controlBuff;
    }

    public boolean isNotEffective() {
        return !effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }
}
