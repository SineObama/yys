package com.sine.yys.inter;

import com.sine.yys.inter.base.IBuffProperty;

import java.util.Collection;

/**
 * 存储{@linkplain IBuff buff}，提供添加、查找、统计属性等操作。
 * <p>
 * 通过{@link IBuffProperty}返回所有buff相应属性的合计。
 */
public interface BuffController extends IBuffProperty {
    Object remove(Object iBuff);

    /**
     * 按照优先级顺序返回指定类型（及其子类）的buff。
     */
    <T> Collection<T> getWithPrior(Class<T> tClass);

    /**
     * 返回指定类型（及其子类）中优先级最高的buff。
     */
    <T> T getFirstWithPrior(Class<T> tClass);

    /**
     * 添加buff。
     * <p>
     * 若同类buff个数已达上限，会调用新buff的{@link IBuff#replace(java.util.List)}决定是否替换掉原有buff。
     *
     * @param buff 新添加的buff。
     */
    <T> void add(IBuff buff);

    <T> T get(Class<T> clazz);

    <T> boolean contain(Class<T> clazz);

    /**
     * 移除一个指定类型的buff。
     */
    <T> void remove(Class<T> clazz);

    /**
     * 死亡时清空buff。不清除旗帜效果。
     */
    void clear();

    /**
     * 获取指定类型的所有buff，包括子类。
     */
    <T> Collection<? extends T> getBuffs(Class<T> clazz);
}
