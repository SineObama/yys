package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.ContextAndRunner;

public interface EventHandler {
    void handle(ContextAndRunner context);
}
