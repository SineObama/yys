package com.sine.yys.mitama;

import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

/**
 * 监听自身事件的御魂通用逻辑。
 */
public abstract class BaseSelfMitama extends BaseMitama {
    @Override
    public final void doInit(Controller controller, Entity self) {
        if (this instanceof EventHandler)
            self.getEventController().add((EventHandler<?>) this);
    }
}
