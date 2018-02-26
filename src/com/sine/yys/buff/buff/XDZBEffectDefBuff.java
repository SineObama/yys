package com.sine.yys.buff.buff;

import com.sine.yys.inter.Entity;

/**
 * 兄弟之绊-效果抵抗。
 */
public class XDZBEffectDefBuff extends EffectDefIBuff implements DispellableBuff {
    public XDZBEffectDefBuff(int last, double effectDef, Entity src) {
        super(last, "兄弟之绊-效果抵抗", effectDef, src);
    }
}
