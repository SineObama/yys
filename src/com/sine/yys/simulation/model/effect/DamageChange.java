package com.sine.yys.simulation.model.effect;

import com.sine.yys.simulation.model.entity.Entity;

/**
 * 伤害增减效果。
 */
public interface DamageChange extends DamageEffect {
    boolean judge(Entity target);
}
