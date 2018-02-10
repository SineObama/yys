package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 技能，根接口。
 */
public interface Skill {
    String getName();

    String getDetail();

    int getCD();

    int getMAXCD();

    int step();

    void init(InitContext context);

    Entity getSelf();
}
