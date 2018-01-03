package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.ContextAndRunner;

public interface EventController {
    <T extends Event> void add(Class<T> T, EventHandler handler);

    <T extends Event> void remove(Class<T> T, EventHandler handler);

    <T extends Event> void trigger(Class<T> T, ContextAndRunner context);
}
