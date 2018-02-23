package com.sine.yys.simulation.component;

import com.sine.yys.info.Property;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.mitama.Mitama;
import com.sine.yys.shikigami.Shikigami;

/**
 * 式神。
 * 非召唤物。
 */
public class ShikigamiEntityImpl extends EntityImpl implements ShikigamiEntity {
    public ShikigamiEntityImpl(Property property, Mitama mitama, Shikigami shiShen) {
        super(property, mitama, shiShen);
    }
}
