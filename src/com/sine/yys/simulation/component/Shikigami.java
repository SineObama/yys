package com.sine.yys.simulation.component;

import com.sine.yys.simulation.component.entity.ShiShen;
import com.sine.yys.simulation.info.IProperty;

/**
 * 式神。
 * 非召唤物。
 */
public class Shikigami extends Entity {
    public Shikigami(IProperty property, Mitama mitama, ShiShen shiShen) {
        super(property, mitama, shiShen);
    }
}
