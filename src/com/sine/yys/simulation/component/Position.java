package com.sine.yys.simulation.component;

/**
 * 一个实体的位置。
 * 不包含逻辑，实体死亡需要由外界调用控制。
 * 未来可用于死后复活、召唤物放置等等。
 * <p>位置在战场上是已经固定，不可更改的抽象概念，式神也只能在自己的位置上。</p>
 */
public interface Position {
    /**
     * @return 当前位置上的实体。
     */
    Entity getCurrent();

    /**
     * @return 位置上原本的实体。
     */
    Entity getSource();

    /**
     * @return 位置上原本的实体是否阵亡。
     */
    boolean isDead();

    /**
     * @param dead 设置原本实体的阵亡状态。
     */
    void setDead(boolean dead);
}
