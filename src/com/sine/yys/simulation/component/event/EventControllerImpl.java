package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.ContextAndRunner;
import com.sine.yys.simulation.util.Msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EventControllerImpl implements EventController {
    private final Logger log = Logger.getLogger(this.getClass().toString());

    private final Map<Class, List<EventHandler>> controllerMap = new HashMap<>();

    @Override
    public <T extends Event> void add(Class<T> T, EventHandler handler) {
        get(T).add(handler);
    }

    @Override
    public <T extends Event> void remove(Class<T> T, EventHandler handler) {
        get(T).remove(handler);
    }

    @Override
    public <T extends Event> void trigger(Class<T> T, ContextAndRunner context) {
        for (EventHandler handler : get(T)) {
            handler.handle(context);
        }
    }

    private List<EventHandler> get(Class T) {
        if (!controllerMap.containsKey(T))
            controllerMap.put(T, new ArrayList<>());
        return controllerMap.get(T);
    }
}
