package com.sine.yys.simulation.component.core;

import com.sine.yys.simulation.component.BaseEntity;
import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.component.core.mitama.Mitama;
import com.sine.yys.simulation.component.core.shishen.ShiShen;
import com.sine.yys.simulation.info.IProperty;

/**
 * 式神。
 * 非召唤物。
 */
public class Shikigami extends BaseEntity implements Entity {
    public Shikigami(IProperty property, Mitama mitama, ShiShen shiShen) {
        super(property, mitama, shiShen);
    }
}
