package com.sine.yys.buff.shield;

import com.sine.yys.inter.Entity;

public class DiZangXiangShield extends BaseShield implements DispellableShield {
    public DiZangXiangShield(int value, Entity src) {
        super(value, 1, "地藏像盾", src);
    }
}
