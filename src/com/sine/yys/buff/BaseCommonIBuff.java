package com.sine.yys.buff;

import com.sine.yys.inter.Entity;

/**
 * 一般的buff。
 * 有持续回合数，在回合结束时减1。
 */
public abstract class BaseCommonIBuff extends BaseIBuff {
    public BaseCommonIBuff(int last, String name, Entity src) {
        super(last, src, name);
    }

    final void dealLastAfterAction() {
        last -= 1;
    }
}
