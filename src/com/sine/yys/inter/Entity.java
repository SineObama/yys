package com.sine.yys.inter;

import com.sine.yys.info.Property;
import com.sine.yys.info.Target;

/**
 * 战场中的可见实体。
 * 包括式神和召唤物。
 * 可理解为可作为目标（攻击）的对象。
 * <p>
 * 全局广泛使用的接口，主要作存储功能（buff、事件）。
 * 还为技能保存部分变量（状态）。
 */
public interface Entity extends Target, Property {
    /**
     * 获取变量。没有时使用默认值。
     *
     * @param <V>          值类型。
     * @param key          键。
     * @param defaultValue 默认值。
     * @return 变量值。
     */
    <V> V get(Object key, V defaultValue);

    /**
     * 保存变量。
     *
     * @param key   键
     * @param value 值。
     */
    void put(Object key, Object value);

    /**
     * @return 生命百分比。
     */
    @Override
    double getLife();

    int getMaxLife();

    int getLifeInt();

    boolean isDead();

    BuffController getBuffController();

    FireRepo getFireRepo();

    /**
     * @return 行动位置，范围0-1。
     */
    double getPosition();

    /**
     * 增加行动条（拉条）。
     */
    void addPosition(double count);

    EventController getEventController();

    double getCureCoefficient();

    /**
     * 造成伤害增加。
     */
    double getDamageCoefficient();

    /**
     * 战场旗帜buff的伤害增加系数。
     * 用于针女。
     */
    double getFlagDamageCoefficient();

    /**
     * @return 是否被封印御魂。
     */
    boolean mitamaSealed();

    /**
     * @return 是否被封印被动。
     */
    boolean passiveSealed();
}
