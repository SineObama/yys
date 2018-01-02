package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直接保存每个式神的进度条位置。
 * 每次计算每人到达行动的时间，取最短时间的行动。
 */
public class SpeedBarImpl implements SpeedBar {
    private Map<Entity, Double> bar = new HashMap<>();
    private Map<Entity, Boolean> on = new HashMap<>();

    @Override
    public void add(Entity entity) {
        add(entity, 0);
    }

    @Override
    public void add(Entity entity, double position) {
        bar.put(entity, position);
        on.put(entity, true);
    }

    @Override
    public void addAll(List<Entity> list) {
        for (Entity entity : list)
            add(entity);
    }

    @Override
    public void stop(Entity entity) {
        on.put(entity, false);
    }

    @Override
    public void restart(Entity entity) {
        on.put(entity, true);
    }

    @Override
    public void remove(Entity entity) {
        bar.remove(entity);
        on.remove(entity);
    }

    @Override
    public Entity step() {
        Entity rtn = null;
        double min = 1;  // 不可能达到的较大值
        for (Entity entity : bar.keySet()) {
            if (on.get(entity)) {
                double remain = (1 - bar.get(entity)) / entity.getSpeed();
                if (min > remain) {
                    min = remain;
                    rtn = entity;
                }
            }
        }
        for (Entity entity : bar.keySet()) {
            if (on.get(entity)) {
                bar.put(entity, bar.get(entity) + min * entity.getSpeed());
            }
        }
        bar.put(rtn, 0.0);
        return rtn;
    }

    @Override
    public double getPosition(Entity entity) {
        return bar.get(entity);
    }

    @Override
    public Map<Entity, Double> getAll() {
        return bar;
    }
}
