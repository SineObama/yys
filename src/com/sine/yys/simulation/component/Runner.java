package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 供技能进行操作（造成伤害、控制等）。
 */
public interface Runner {
    SpeedBar getSpeedBar();

    void damage(double coefficient);

    /**
     * 应用系数伤害
     */
    void damage(Entity target, double coefficient);

    /**
     * 随机吸取鬼火。
     * 目前只有青行灯用。
     */
    void randomGrab(double pct, int times);

    void useFire(int num);

    void setCostFire(int num);
}
