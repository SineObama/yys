package com.sine.yys.simulation.component;

import com.sine.yys.info.IProperty;
import com.sine.yys.inter.Entity;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shishen.BaseShiShen;

/**
 * 式神。
 * 非召唤物。
 */
public class Shikigami extends BaseEntity implements Entity {
    public Shikigami(IProperty property, BaseMitama mitama, BaseShiShen shiShen) {
        super(property, mitama, shiShen);
    }
}
