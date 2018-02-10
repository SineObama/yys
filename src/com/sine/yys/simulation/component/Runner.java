package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.Attack;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 供技能进行操作（造成伤害、控制等）。
 */
public interface Runner {
    SpeedBar getSpeedBar();

    void damage(Attack attack);

    /**
     * 应用系数伤害
     */
    void damage(Entity target, Attack attack);

    void damageFrom(Entity self, Attack attack);

    void damage(Entity self, Entity target, Attack attack);

    void realDamage(Entity self, Entity target, double maxByAttack, double maxPctByMaxLife);

    /**
     * 随机吸取鬼火。
     * 目前只有青行灯用。
     */
    void randomGrab(double pct, int times);

    void useFire(int num);

    void setTarget(Entity entity);
}
