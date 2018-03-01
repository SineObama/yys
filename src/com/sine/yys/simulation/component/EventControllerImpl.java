package com.sine.yys.simulation.component;

import com.sine.yys.info.Sealable;
import com.sine.yys.inter.EventController;
import com.sine.yys.inter.EventHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

// TODO unchecked 警告，更好的实现方法？

/**
 * 在阵营和式神中都有独立的控制器，分开定义和管理属于阵营全局和式神自身的事件。
 */
public class EventControllerImpl implements EventController {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final Map<Class, Set<Container<EventHandler>>> controllerMap = new HashMap<>();
    private final Map<Class, Boolean> states = new HashMap<>();
    private final Map<EventHandler, Integer> prior = new HashMap<>();

    @Override
    public void add(EventHandler<?> handler) {
        get(getEventType(handler)).add(new Container<>(null, handler));
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
    public void remove(EventHandler<?> handler) {
        get(getEventType(handler)).remove(new Container<>(prior.get(handler), handler));
    }

    @Override
    public <EventType> void setState(Class<EventType> clazz, boolean state) {
        states.put(clazz, state);
    }

    @Override
    public <EventType> void trigger(EventType event) {
        final Set<Container<EventHandler>> containers = new TreeSet<>(get(event.getClass()));
        if (!states.containsKey(event.getClass()) || states.get(event.getClass())) {
            for (Container<EventHandler> container : containers) {
                final EventHandler obj = container.getObj();
                if (obj instanceof Sealable && ((Sealable) obj).sealed())
                    continue;
                obj.handle(event);
            }
        }
    }

    @Override
    public <EventType> void triggerOff(EventType event) {
        trigger(event);
        states.put(event.getClass(), false);
    }

    private Set<Container<EventHandler>> get(Class T) {
        if (!controllerMap.containsKey(T))
            controllerMap.put(T, new TreeSet<>());
        return controllerMap.get(T);
    }

    private <EventType> Class<EventType> getEventType(EventHandler<EventType> handler) {
        Class clazz = handler.getClass();
        do {  // 往上查找父类
            Type[] types = clazz.getGenericInterfaces();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType p = (ParameterizedType) type;
                    if (p.getRawType() == EventHandler.class)
                        return (Class<EventType>) p.getActualTypeArguments()[0];
                }
            }
            clazz = (Class) clazz.getGenericSuperclass();
        } while (clazz != null);
        throw new RuntimeException("can't find EventHandler interface");
    }
}
