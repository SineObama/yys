package com.sine.yys.buff.buff;

import com.sine.yys.buff.EffectDefIBuff;
import com.sine.yys.inter.Entity;

public abstract class AddEffectDef extends EffectDefIBuff implements DispellableBuff {
    protected AddEffectDef(int last, String prefix, double effectDef, Entity src) {
        super(last, prefix + "-增加", effectDef, src);
    }
}
