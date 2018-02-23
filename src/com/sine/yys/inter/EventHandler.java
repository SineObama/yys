package com.sine.yys.inter;

/**
 * 事件处理者（监听器）。
 *
 * @param <EventType> 处理的事件类型，也是处理函数的传入参数。
 */
public interface EventHandler<EventType> {
    void handle(EventType event);
}
