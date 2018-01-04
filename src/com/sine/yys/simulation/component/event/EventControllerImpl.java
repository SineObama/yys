package com.sine.yys.simulation.component.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EventControllerImpl implements EventController {
    private final Logger log = Logger.getLogger(this.getClass().toString());

    private final Map<Class, List<EventHandler<?>>> controllerMap = new HashMap<>();

    @Override
    public <EventType extends Event> void add(Class<EventType> EventType, EventHandler<EventType> handler) {
        get(EventType).add(handler);
    }

    @Override
    public <EventType extends Event> void remove(Class<EventType> EventType, EventHandler<EventType> handler) {
        get(EventType).remove(handler);
    }

    @Override
    public <EventType extends Event> void trigger(Class<EventType> EventType, EventType event){
        for (EventHandler handler : get(EventType)) {
            handler.handle(event);
        }
    }

    private List<EventHandler<?>> get(Class T) {
        if (!controllerMap.containsKey(T))
            controllerMap.put(T, new ArrayList<>());
        return controllerMap.get(T);
    }
}
