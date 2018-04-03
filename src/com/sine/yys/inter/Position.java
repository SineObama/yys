package com.sine.yys.inter;

/**
 * 一个实体的位置。
 * <p>
 * 位置在战场上是已经固定，不可更改的抽象概念，式神也只能在自己的位置上。
 * <p>
 * 用于死后复活、召唤物放置。
 */
public interface Position<T extends Entity> {
    /**
     * @return 当前位置上的实体。
     */
    T getCurrent();

    /**
     * 设置当前位置的实体。
     *
     * @param entity 实体。
     */
    void setCurrent(T entity);

    /**
     * @return 位置上原本的实体。
     */
    T getSource();
}
