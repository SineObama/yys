package com.sine.yys.attacktype;

/**
 * 属于 Buff 大类的 AttackType。
 * <p>
 * 有待进一步定义。
 */
public class BuffAttack extends AttackTypeImpl {
    public BuffAttack(double damage) {
        buff = true;
        this.damage = damage;
    }
}
