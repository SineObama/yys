package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 技能。
 * 获取CD等，提供step函数。
 */
public interface Skill {
    String getName();

    String getDetail();

    int getCD();

    int getMAXCD();

    /**
     * 每回合调用，以减少CD。
     *
     * @return 剩余CD。0表示可用
     */
    int step();

    void init(InitContext context);

    /**
     * @return 所属式神。
     */
    Entity getSelf();
}
