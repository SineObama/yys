package com.sine.yys.simulation.component;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

/**
 * 获取战场和行动期间的信息。
 */
public interface Context {
    int getRound();

    Camp getOwn();

    Camp getEnemy();

    Entity getSelf();

    Entity getTarget();

    ActiveSkill getActiveSkill();
}
