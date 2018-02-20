package com.sine.yys.simulation.component.core.model.buff.debuff;

import com.sine.yys.simulation.component.core.model.buff.BaseIBuff;
import com.sine.yys.simulation.component.core.model.buff.DispellableDebuff;

/**
 * 般若的封印御魂和被动效果。
 */
public class FengYin extends BaseIBuff implements SealPassive, SealMitama, DispellableDebuff {
    /**
     * @param last 持续回合数。必须为正。
     */
    public FengYin(int last) {
        super(last, "封印");
    }
}
