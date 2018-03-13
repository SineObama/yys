package com.sine.yys.inter;

/**
 * 可结合（叠加）的接口，用于buff叠加。
 * 实现中也可采用对比的方法，返回被留下的对象。
 * 参数必须是新的对象（在价值相同的情况下会选择新对象）。
 */
public interface Combinable<T> {
    T combineWith(T o);
}
