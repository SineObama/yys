package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.Controller;

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
