package com.sine.yys.simulation.component.model;

import com.sine.yys.simulation.component.model.event.Event;

/**
 * 事件处理者（监听器）。
 *
 * @param <EventType> 处理的事件类型，也是处理函数的传入参数。
 */
public interface EventHandler<EventType extends Event> {
    void handle(EventType event);
}
