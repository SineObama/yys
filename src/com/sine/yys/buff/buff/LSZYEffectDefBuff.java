package com.sine.yys.buff.buff;

import com.sine.yys.inter.Buff;
import com.sine.yys.inter.Entity;

/**
 * 龙首之玉效果抵抗buff。
 */
public class LSZYEffectDefBuff extends EffectDefIBuff implements Buff {
    public LSZYEffectDefBuff(double effectDef, Entity src) {
        super(Integer.MAX_VALUE, "龙首之玉-效果抵抗", effectDef, src);
    }
}
