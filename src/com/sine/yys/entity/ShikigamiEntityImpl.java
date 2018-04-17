package com.sine.yys.entity;

import com.sine.yys.impl.EntityInfo;
import com.sine.yys.inter.ShikigamiEntity;

/**
 * 战场中的式神实体（非召唤物）。
 */
public class ShikigamiEntityImpl extends EntityImpl implements ShikigamiEntity {
    public ShikigamiEntityImpl(EntityInfo info, double lifeTimes) {
        super(info, lifeTimes);
    }
}
