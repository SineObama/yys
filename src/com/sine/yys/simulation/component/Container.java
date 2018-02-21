package com.sine.yys.simulation.component;

/**
 * 包装对象，以实现优先级排序。
 * 构造时必须指定优先级，不指定的用法是在删除时（会进行对象对比）。
 * 允许相同优先级存在，对比时使用哈希值。
 *
 * @param <T> 对象类型。
 */
public class Container<T> implements Comparable<Container> {
    private final int cmp;  // 判断容器内物体。用于删除匹配
    private final int priority;
    private T obj;

    public Container(T obj) {
        this.cmp = 0;
        this.priority = 0;
        this.obj = obj;
    }

    /**
     * @param priority 优先级，越小优先级越高，必须为非负数。
     * @param obj      对象。
     */
    public Container(Integer priority, T obj) {
        if (priority == null)
            priority = Integer.MAX_VALUE;
        else if (priority < 0)
            throw new RuntimeException("优先级必须为非负数。");
        this.cmp = 1;
        this.priority = priority;
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public int compareTo(Container o) {
        if (this == o)
            return 0;
        int result = (this.priority - o.priority) * this.cmp * o.cmp;
        if (result == 0)  // 允许相同优先级存在
            return this.obj.hashCode() - o.obj.hashCode();
        return result;
    }
}
