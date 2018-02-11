package com.sine.yys.simulation.model.shield;

import com.sine.yys.simulation.model.buff.Buff;

/**
 * 盾。
 */
public interface Shield extends Buff {
    /**
     * 护盾剩余值。
     */
    int getValue();

    /**
     * 实施伤害。
     * @return 溢出的伤害（未破盾则0）。
     */
    int doDamage(int damage);
}
