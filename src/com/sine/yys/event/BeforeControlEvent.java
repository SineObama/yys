package com.sine.yys.event;

import com.sine.yys.buff.debuff.ControlBuff;

/**
 * 实施控制效果前触发。
 * 目前用于花鸟卷抵销效果。
 * <p>
 * 冻结、眩晕、睡眠、变形、沉默、嘲讽、混乱、牢笼束缚等的技能和御魂效果。
 *
 * @version 2018-2-28
 */
public class BeforeControlEvent {
    final ControlBuff controlBuff;
    boolean effective = true;

    public BeforeControlEvent(ControlBuff controlBuff) {
        this.controlBuff = controlBuff;
    }

    public ControlBuff getControlBuff() {
        return controlBuff;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public boolean isEffective() {
        return effective;
    }
}
