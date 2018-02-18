package com.sine.yys.simulation.model;

import com.sine.yys.simulation.model.event.Event;

/**
 * 事件控制器。
 * 事件以类型作为标识，一个类型只对应一个事件。
 * <p>添加{@link EventHandler}以监听事件，
 * 可指定处理的事件类型（默认从对象中获取模板信息中的事件类型），
 * 可设置优先级（默认最低）。</p>
 * 触发事件时传入{@link Event}实例以传递、返回信息。
 * 还可以关闭事件，阻止触发。
 */
public interface EventController {
    void add(EventHandler<?> handler);

    <EventType extends T, T extends Event> void add(Class<EventType> clazz, EventHandler<T> handler);

    void add(EventHandler<?> handler, int priority);

    <EventType extends T, T extends Event> void add(Class<EventType> clazz, EventHandler<T> handler, int priority);

    void remove(EventHandler<?> handler);

    /**
     * 设置事件状态，开启或关闭。
     */
    <EventType extends Event> void setState(Class<EventType> clazz, boolean state);

    <EventType extends Event> void trigger(EventType event);

    /**
     * 触发，然后关闭事件。
     */
    <EventType extends Event> void triggerOff(EventType event);
}
