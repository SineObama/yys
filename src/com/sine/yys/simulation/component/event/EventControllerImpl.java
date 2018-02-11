package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.Container;
import com.sine.yys.simulation.component.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

// TODO unchecked 警告，更好的实现方法？
public class EventControllerImpl implements EventController {
    private final Logger log = Logger.getLogger(this.getClass().toString());

    private final Map<Class, Set<Container<EventHandler>>> controllerMap = new HashMap<>();

    @Override
    public <EventType extends Event> void add(Class<EventType> EventType, EventHandler<EventType> handler) {
        get(EventType).add(new Container<>(Integer.MAX_VALUE, handler));
    }

    @Override
    public <EventType extends Event> void add(Class<EventType> EventType, EventHandler<EventType> handler, int priority) {
        get(EventType).add(new Container<>(priority, handler));
    }

    @Override
    public <EventType extends Event> void remove(Class<EventType> EventType, EventHandler<EventType> handler) {
        get(EventType).remove(new Container<>(-1, handler));
    }

    @Override
    public <EventType extends Event> void trigger(Class<EventType> EventType, EventType event, Controller controller) {
        for (Container<EventHandler> container : get(EventType)) {
            final EventHandler obj = container.getObj();
            obj.handle(event, controller);
        }
    }

    private Set<Container<EventHandler>> get(Class T) {
        if (!controllerMap.containsKey(T))
            controllerMap.put(T, new TreeSet<>());
        return controllerMap.get(T);
    }
}
