package com.sine.yys.entity;

import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.inter.base.Property;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shikigami.BaseShikigami;

/**
 * 战场中的式神实体（非召唤物）。
 */
public class ShikigamiEntityImpl extends EntityImpl implements ShikigamiEntity {
    public ShikigamiEntityImpl(BaseShikigami shikigami, Property property, BaseMitama mitama, double lifeTimes, String name) {
        super(shikigami, property, mitama, lifeTimes, name);
    }
}
