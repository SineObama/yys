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

    int getMaxLife();

    int getLostLifeInt();

    int getLifeInt();

    /**
     * @param target 协战目标。若目标异常（已死或为友方）则随机协战敌方目标。
     */
    void xieZhan(Entity target);

    /**
     * 根据当前控制效果，重新确认攻击目标。
     * 目标死亡则随机攻击敌方。
     *
     * @param origin 期望攻击目标。
     * @return 最终攻击目标。无法攻击则为null。
     */
    Entity applyControl(Entity origin);

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

    int addLife(int count);

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
