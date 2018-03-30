package com.sine.yys.entity;

import com.sine.yys.inter.Shikigami;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.inter.base.Mitama;
import com.sine.yys.inter.base.Property;

/**
 * 战场中的式神实体（非召唤物）。
 */
public class ShikigamiEntityImpl extends EntityImpl implements ShikigamiEntity {
    public ShikigamiEntityImpl(Property property, Mitama mitama, Shikigami shiShen, double lifeTimes) {
        super(property, mitama, shiShen, shiShen.getName(), lifeTimes);
    }

    public ShikigamiEntityImpl(Property property, Mitama mitama, Shikigami shiShen, double lifeTimes, String name) {
        super(property, mitama, shiShen, name, lifeTimes);
    }
}
