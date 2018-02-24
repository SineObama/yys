package com.sine.yys.inter;

import com.sine.yys.info.Property;
import com.sine.yys.info.Target;

/**
 * 战场中的实体。
 * 包括式神和召唤物。
 * 可理解为可作为目标（攻击）的对象。
 * <p>
 * 全局广泛使用的接口，主要作存储功能。
 * 可获取战斗时属性（包括buff加成），设置行动条位置，获取buff和事件控制器，还为技能保存变量。
 */
public interface Entity extends Target, Property {
    /**
     * 获取技能保存的变量。没有时使用默认值。
     *
     * @param clazz        技能Class。
     * @param key          键。
     * @param defaultValue 默认值。
     * @param <T>          技能类型。
     * @param <V>          值类型。
     * @return 变量值。
     */
    <T, V> V get(Class<T> clazz, Object key, V defaultValue);

    /**
     * 保存技能变量。
     *
     * @param clazz 技能Class。
     * @param key   键
     * @param value 值。
     * @param <T>   技能类型。
     */
    <T> void put(Class<T> clazz, Object key, Object value);

    /**
     * @return 生命百分比。
     */
    @Override
    double getLife();

    int getMaxLife();

    int getLifeInt();

    int shieldValue(double src);

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

    void setPosition(double position);

    EventController getEventController();
}
