package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.component.mitama.BaseMitama;
import com.sine.yys.simulation.component.shishen.BaseShiShen;
import com.sine.yys.simulation.info.IProperty;

/**
 * 式神。
 * 非召唤物。
 */
public class Shikigami extends BaseEntity implements Entity {
    public Shikigami(IProperty property, BaseMitama mitama, BaseShiShen shiShen) {
        super(property, mitama, shiShen);
    }
}
