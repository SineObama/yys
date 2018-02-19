package com.sine.yys.simulation.component.model.buff.debuff;

import com.sine.yys.simulation.component.model.buff.BaseIBuff;
import com.sine.yys.simulation.component.model.buff.Debuff;

/**
 * 封印御魂。
 */
public abstract class SealMitama extends BaseIBuff implements Debuff {
    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     */
    public SealMitama(int last, String name) {
        super(last, name);
    }
}
