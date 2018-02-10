package com.sine.yys.simulation.component.event;

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

    private class Container<T> implements Comparable<Container> {
        private final int priority;
        private final T obj;

        Container(int priority, T obj) {
            this.priority = priority;
            this.obj = obj;
        }

        T getObj() {
            return obj;
        }

        @Override
        public int compareTo(Container o) {
            if (this == o)
                return 0;
            if (this.priority == -1)  // 判断容器内物体。用于删除匹配
                return this.obj == o.obj ? 0 : this.obj.hashCode() - o.obj.hashCode();  // TODO 用哈希相减做对比不好？
            return this.priority - o.priority;
        }
    }

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
