package com.sine.yys.inter;

/**
 * 事件控制器。
 * <p>
 * 注册{@link EventHandler}监听事件，触发等接口。
 * 触发时传入事件实例，实现信息传递、返回。
 * <p>
 * 可指定处理的事件类型（默认从对象中获取模板信息中的事件类型），
 * 可设置优先级（值小优先级高，默认最低）。
 * <p>
 * 事件在双方阵营和每个式神中分别管理。
 * 事件以类型作为标识，一个类型只对应一个事件。
 */
public interface EventController {
    void add(EventHandler<?> handler);

    /**
     * 只监听事件的某一次触发。
     * <p>
     * 用于实现比如多段攻击不重复计算（动作结束后清空次数）。
     *
     * @param triggerAt 次数序号，从0开始。
     * @param handler   监听器。
     */
    void add(int triggerAt, EventHandler<?> handler);

    <EventType extends T, T> void add(Class<EventType> clazz, EventHandler<T> handler);

    void add(EventHandler<?> handler, int priority);

    <EventType extends T, T> void add(Class<EventType> clazz, EventHandler<T> handler, int priority);

    void remove(EventHandler<?> handler);

    <EventType> EventType trigger(EventType event);
}
