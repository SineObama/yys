package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.component.Controller;

/**
 * 事件处理者接口。
 *
 * @param <EventType> 处理的事件类型，也是处理函数的传入参数。
 */
public interface EventHandler<EventType> {
    void handle(EventType context, Controller controller);
}
