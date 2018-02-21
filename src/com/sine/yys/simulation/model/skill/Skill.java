package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.battle.Named;

/**
 * 技能。
 * 获取CD等，提供step函数。
 */
public interface Skill extends Named {
    void init(InitContext context);

    String getDetail();

    int getCD();

    int getMAXCD();

    /**
     * 每回合调用，以减少CD。
     * 参考彼岸花花海，暂定回合开始时调用。
     *
     * @return 剩余CD。0表示可用
     */
    int step();
}
