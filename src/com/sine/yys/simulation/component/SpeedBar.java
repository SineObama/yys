package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;

import java.util.List;
import java.util.Map;

/**
 * 行动条对象。
 * 添加式神实体到行动条中（默认为初始点位置0，或者指定0~1的位置）
 */
public interface SpeedBar {
    void add(Entity entity);

    void add(Entity entity, double position);

    void addAll(List<Entity> list);

    void stop(Entity entity); // 死亡时可能保留行动位置不变

    void restart(Entity entity); // 对应复活

    void remove(Entity entity);

    Entity step(); // 下一个行动人

    double getPosition(Entity entity);

    Map<Entity, Double> getAll();
}
