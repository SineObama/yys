package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.Controller;

/**
 * 事件控制器，可以按事件类型把回调对象（Handler）分别存储、分别触发。
 */
public interface EventController {
    /**
     * 默认优先级最低。
     */
    <EventType extends Event> void add(Class<EventType> EventType, EventHandler<EventType> handler);

    <EventType extends Event> void add(Class<EventType> EventType, EventHandler<EventType> handler, int priority);

    <EventType extends Event> void remove(Class<EventType> EventType, EventHandler<EventType> handler);

    <EventType extends Event> void trigger(Class<EventType> EventType, EventType event, Controller controller);
}
