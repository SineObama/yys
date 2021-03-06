package com.sine.yys.mitama;

import com.sine.yys.impl.BaseComponent;
import com.sine.yys.inter.Mitama;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.inter.base.Sealable;
import com.sine.yys.util.JSON;

/**
 * 御魂通用逻辑。
 */
public abstract class BaseMitama extends BaseComponent implements Mitama, Sealable, JSONable {
    @Override
    public boolean sealed() {
        return getSelf().mitamaSealed();
    }

    @Override
    public String toJSON() {
        return JSON.format("name", getName());
    }
}
