package com.sine.yys.inter;

import java.util.Collection;

/**
 * 为单个式神存储buff和debuff（包括护盾），提供添加、查找等操作。
 * 同类型的buff只能存在一个，buff应当实现{@link Combinable}以完成叠加（或理解为选择）的逻辑。
 */
public interface BuffController {
    /**
     * 添加buff。叠加逻辑由buff实现。
     */
    <T> void add(Combinable<T> buff);

    /**
     * 获取唯一的buff，不包括附加buff。
     */
    <T> T get(Class<T> clazz);

    <T> boolean contain(Class<T> clazz);

    /**
     * 移除特定类型的buff。
     */
    <T> void remove(Class<T> clazz);

    /**
     * 死亡时清空buff。
     */
    void clear();

    <T> Collection<? extends T> getBuffs(Class<T> clazz);
}
