package com.sine.yys.buff.debuff;

import com.sine.yys.buff.DefenseIBuff;
import com.sine.yys.inter.Entity;

/**
 * 防御减少。
 */
public abstract class ReduceDefense extends DefenseIBuff implements DispellableDebuff {
    protected ReduceDefense(int last, String name, double reducePct, Entity src) {
        super(last, name + "-减少", -reducePct, src);
    }
}
