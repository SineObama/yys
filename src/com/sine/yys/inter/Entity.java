package com.sine.yys.inter;

import com.sine.yys.inter.base.Property;
import com.sine.yys.inter.base.Target;

/**
 * 实体。
 * <p>
 * 战场中可作为目标（攻击）的对象，包括式神和召唤物。
 * <p>
 * 主要用作存储{@linkplain IBuff buff}、事件。
 * 也可以为技能保存部分变量。
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

    /**
     * 不会超过最大值。
     */
    void setLife(int life);

    int getMaxLife();

    int getLostLifeInt();

    int getLifeInt();

    /**
     * 进行协战。
     * 包括受控状态、目标死亡、目标不是敌方、自己混乱的处理。
     *
     * @param target 原协战目标。
     */
    void xieZhan(Entity target);

    /**
     * 进行反击。
     * 包括受控状态、目标死亡、自己混乱的处理。
     *
     * @param target 原反击目标。
     */
    void counter(Entity target);

    boolean isDead();

    BuffController getBuffController();

    FireRepo getFireRepo();

    /**
     * @return 行动位置，范围0-1。
     */
    double getPosition();

    /**
     * @param position 行动条位置。
     */
    void setPosition(double position);

    /**
     * 增加行动条（拉条）。
     */
    void addPosition(double count);

    int addLife(int count);

    int reduceLife(int count);

    EventController getEventController();

    /**
     * @return 治疗系数。
     */
    double getCureCoefficient();

    /**
     * @return 造成伤害系数。
     */
    double getDamageCoefficient();

    /**
     * @return 受到伤害系数。
     */
    double getBeDamageCoefficient();

    /**
     * 用于改变针女伤害（不受一般伤害加成影响）。
     *
     * @return {@linkplain com.sine.yys.buff.BattleFlag 战场旗帜效果}的伤害系数。
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
