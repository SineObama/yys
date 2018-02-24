package com.sine.yys.info;

/**
 * 可结合（叠加）的接口，用于buff叠加。
 * 也可当做对比使用，返回被留下的对象。
 */
public interface Combinable<T> {
    T overlying(T o);
}
