package com.sine.yys.inter;

/**
 * 一个实体的位置。
 * 纯数据结构，不包含逻辑。
 * <p>
 * 位置在战场上是已经固定，不可更改的抽象概念，式神也只能在自己的位置上。
 * <p>
 * 在死后复活、召唤物放置等会用上。
 */
public interface Position {
    /**
     * @return 当前位置上的实体。
     */
    <T extends Entity> T getCurrent();

    /**
     * 设置当前位置的实体。
     *
     * @param entity 实体。
     */
    <T extends Entity> void setCurrent(T entity);

    /**
     * @return 位置上原本的实体。
     */
    <T extends Entity> T getSource();
}
