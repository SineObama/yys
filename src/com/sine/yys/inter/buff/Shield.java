package com.sine.yys.inter.buff;

/**
 * 盾。
 */
public interface Shield extends UniqueIBuff, Buff {
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
