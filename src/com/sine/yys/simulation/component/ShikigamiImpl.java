package com.sine.yys.simulation.component;

import com.sine.yys.info.IProperty;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.Shikigami;
import com.sine.yys.mitama.BaseMitama;
import com.sine.yys.shishen.BaseShiShen;

/**
 * 式神。
 * 非召唤物。
 */
public class ShikigamiImpl extends EntityImpl implements Shikigami {
    public ShikigamiImpl(IProperty property, BaseMitama mitama, BaseShiShen shiShen) {
        super(property, mitama, shiShen);
    }
}
