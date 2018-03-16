package com.sine.yys.inter;

/**
 * 事件控制器，存储事件并提供触发接口等。
 * 事件在双方阵营和每个式神中分别管理。
 * 事件以类型作为标识，一个类型只对应一个事件。
 * <p>
 * 添加{@link EventHandler}以监听事件，
 * 可指定处理的事件类型（默认从对象中获取模板信息中的事件类型），
 * 可设置优先级（默认最低）。
 * <p>
 * 触发时传入事件实例，实现信息传递、返回。
 */
public interface EventController {
    void add(EventHandler<?> handler);

    void add(int triggerAt, EventHandler<?> handler);

    <EventType extends T, T> void add(Class<EventType> clazz, EventHandler<T> handler);

    void add(EventHandler<?> handler, int priority);

    <EventType extends T, T> void add(Class<EventType> clazz, EventHandler<T> handler, int priority);

    void remove(EventHandler<?> handler);

    <EventType> void trigger(EventType event);
}
