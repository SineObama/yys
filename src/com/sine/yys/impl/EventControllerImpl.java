package com.sine.yys.impl;

import com.sine.yys.inter.EventController;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.base.Sealable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * 在阵营和式神中都有独立的控制器，分别管理属于阵营全局和式神自身的事件。
 */
public class EventControllerImpl implements EventController {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final Map<Class, Set<Container<EventHandler>>> controllerMap = new HashMap<>();
    // 事件触发计数
    private final Map<Object, Integer> times = new HashMap<>();
    private final Map<EventHandler, Integer> prior = new HashMap<>();
    private final Map<EventHandler, Integer> triggerAt = new HashMap<>();
    private EventController parent = null;

    public void setParent(EventController parent) {
        this.parent = parent;
    }

    @Override
    public void add(EventHandler<?> handler) {
        get(getEventType(handler)).add(new Container<>(null, handler));
    }

    @Override
    public void add(int triggerAt, EventHandler<?> handler) {
        get(getEventType(handler)).add(new Container<>(null, handler));
        this.triggerAt.put(handler, triggerAt);
    }

    @Override
    public <EventType extends T, T> void add(Class<EventType> clazz, EventHandler<T> handler) {
        get(clazz).add(new Container<>(null, handler));
    }

    @Override
    public void add(EventHandler<?> handler, int priority) {
        prior.put(handler, priority);
        get(getEventType(handler)).add(new Container<>(priority, handler));
    }

    @Override
    public <EventType extends T, T> void add(Class<EventType> clazz, EventHandler<T> handler, int priority) {
        prior.put(handler, priority);
        get(clazz).add(new Container<>(priority, handler));
    }

    @Override
    public void remove(EventHandler handler) {
        getNoCreate(getEventType(handler)).remove(new Container<>(prior.get(handler), handler));
        prior.remove(handler);
        triggerAt.remove(handler);
    }

    @Override
    public <EventType> EventType trigger(EventType event) {
        if (times.containsKey(event)) {
            times.put(event, times.get(event) + 1);
        } else {
            times.put(event, 0);
        }
        final Set<Container<EventHandler>> containers = new TreeSet<>(getNoCreate(event.getClass()));
        for (Container<EventHandler> container : containers) {
            @SuppressWarnings("unchecked") final EventHandler<EventType> obj = container.getObj();
            if (triggerAt.containsKey(obj) && !triggerAt.get(obj).equals(times.get(event)))
                continue;
            if (obj instanceof Sealable && ((Sealable) obj).sealed())
                continue;
            obj.handle(event);
        }
        if (parent != null)
            parent.trigger(event);
        return event;
    }

    /**
     * 清空事件触发计数。
     */
    @Override
    public void clear() {
        times.clear();
    }

    private Set<Container<EventHandler>> get(Class T) {
        if (!controllerMap.containsKey(T))
            controllerMap.put(T, new TreeSet<>());
        return controllerMap.get(T);
    }

    private Set<Container<EventHandler>> getNoCreate(Class T) {
        if (!controllerMap.containsKey(T))
            return new TreeSet<>();
        return controllerMap.get(T);
    }

    private Class getEventType(EventHandler handler) {
        Class clazz = handler.getClass();
        do {  // 往上查找父类
            Type[] types = clazz.getGenericInterfaces();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType p = (ParameterizedType) type;
                    if (p.getRawType() == EventHandler.class)
                        return (Class) p.getActualTypeArguments()[0];
                }
            }
            clazz = (Class) clazz.getGenericSuperclass();
        } while (clazz != null);
        throw new RuntimeException("can't find EventHandler interface");
    }
}
