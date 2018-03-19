package com.sine.yys.buff.buff;

import com.sine.yys.buff.EffectDefIBuff;
import com.sine.yys.inter.Entity;

public abstract class AddEffectDef extends EffectDefIBuff implements DispellableBuff {
    /**
     * @param last      持续回合数。必须为正。
     * @param prefix    名称前缀。
     * @param effectDef 效果抵抗。
     * @param src       来源式神。
     */
    protected AddEffectDef(int last, String prefix, double effectDef, Entity src) {
        super(last, prefix + "-增加", effectDef, src);
    }
}
