package com.sine.yys.simulation.component;

public class Container<T> implements Comparable<Container> {
    private final int priority;
    private T obj;

    public Container(int priority, T obj) {
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
        if (this.priority == -1)  // 判断容器内物体。用于删除匹配
            return this.obj == o.obj ? 0 : this.obj.hashCode() - o.obj.hashCode();  // TODO 用哈希相减做对比不好？
        return this.priority - o.priority;
    }
}
