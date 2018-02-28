package com.sine.yys.buff;

import com.sine.yys.buff.debuff.DoT;
import com.sine.yys.inter.Entity;

/**
 * 行动前操作并减少回合的buff。
 */
public abstract class BaseBeforeActionBuff extends BaseIBuff {
    public BaseBeforeActionBuff(int last, String name, Entity src) {
        super(last, src, name);
    }

    final void dealLastBeforeAction() {
        last -= 1;
    }
}
