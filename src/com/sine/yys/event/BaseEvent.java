package com.sine.yys.event;

import com.sine.yys.inter.Controller;

/**
 * 事件的基类，只注入控制器。
 */
public abstract class BaseEvent implements Event {
    private final Controller controller;

    public BaseEvent(Controller controller) {
        this.controller = controller;
    }

    public final Controller getController() {
        return controller;
    }
}
