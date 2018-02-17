package com.sine.yys.simulation.model.buff.buff;

import com.sine.yys.simulation.model.buff.Buff;
import com.sine.yys.simulation.model.buff.UniqueIBuff;

/**
 * 龙首之玉效果抵抗buff。
 */
public class LSZYEffectDefBuff extends EffectDefIBuff implements UniqueIBuff, Buff {
    public LSZYEffectDefBuff(double effectDef) {
        super(Integer.MAX_VALUE, "龙首之玉-效果抵抗", effectDef);
    }
}
