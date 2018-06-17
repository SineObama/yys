package com.sine.yys.impl;

public class BuffAttack extends AttackTypeImpl {
    public BuffAttack(double damage) {
        buff = true;
        this.damage = damage;
    }
}
