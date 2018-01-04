package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.InitContext;

/**
 * 技能。根基类。
 */
public interface Skill {
    String getName();

    String getDetail();

    int getCD();

    int getMAXCD();

    int step();

    void init(InitContext context);
}
