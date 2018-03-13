package com.sine.yys.inter;

/**
 * 可结合（叠加）的接口，用于buff叠加。
 * 也可当做对比使用，返回被留下的对象。
 * 参数必须是新的对象（在价值相同的情况下会选择参数）。
 */
public interface Combinable<T> {
    T combineWith(T o);
}
