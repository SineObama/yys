package com.sine.yys.buff.debuff;

import com.sine.yys.buff.DefenseIBuff;
import com.sine.yys.inter.Entity;

/**
 * 防御减少debuff。
 */
public abstract class ReduceDefense extends DefenseIBuff implements DispellableDebuff {
    /**
     * @param last      持续回合数。必须为正。
     * @param name      buff名称。
     * @param reducePct 防御减少百分比。
     * @param src       来源式神。
     */
    public ReduceDefense(int last, String name, double reducePct, Entity src) {
        super(last, name + "-减少", -reducePct, src);
    }
}
