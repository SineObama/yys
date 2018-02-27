package com.sine.yys.buff.buff;

import com.sine.yys.inter.Entity;

/**
 * 兄弟之绊-攻击。
 */
public class XDZBAttackBuff extends AttackIBuff implements DispellableBuff {
    public XDZBAttackBuff(int last, double atkPct, Entity src) {
        super(last, "兄弟之绊-攻击", atkPct, src);
    }
}
