package com.sine.yys.mitama;

import com.sine.yys.base.BaseComponent;
import com.sine.yys.inter.JSONable;
import com.sine.yys.inter.Mitama;
import com.sine.yys.inter.Sealable;
import com.sine.yys.util.JSON;

/**
 * 御魂通用逻辑。
 * 使用嵌套类作为事件监听器。
 * 御魂不再与式神一一关联，只在初始化时把式神传递给监听器。
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
