package com.sine.yys.simulation.component;

import com.sine.yys.inter.Shikigami;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.inter.base.Mitama;
import com.sine.yys.inter.base.Property;

/**
 * 式神。
 * 非召唤物。
 */
public class ShikigamiEntityImpl extends EntityImpl implements ShikigamiEntity {
    public ShikigamiEntityImpl(Property property, Mitama mitama, Shikigami shiShen) {
        super(property, mitama, shiShen, shiShen.getName());
    }

    public ShikigamiEntityImpl(Property property, Mitama mitama, Shikigami shiShen, String name) {
        super(property, mitama, shiShen, name);
    }
}
