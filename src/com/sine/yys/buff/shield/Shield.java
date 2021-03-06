package com.sine.yys.buff.shield;

import com.sine.yys.buff.buff.Buff;

/**
 * 护盾buff。
 */
public interface Shield extends Buff {
    /**
     * 护盾剩余值。
     */
    int getValue();

    /**
     * 实施伤害。
     *
     * @return 溢出的伤害（未破盾则-1）。
     */
    int doDamage(int damage);
}
