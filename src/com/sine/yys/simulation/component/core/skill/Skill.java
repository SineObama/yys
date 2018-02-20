package com.sine.yys.simulation.component.core.skill;

import com.sine.yys.simulation.component.core.InitContext;
import com.sine.yys.simulation.component.inter.Entity;
import com.sine.yys.simulation.info.Named;

/**
 * 技能。
 * 只有逻辑，状态保存在{@link Entity}中。
 * 获取CD等，提供step函数。
 */
public interface Skill extends Named {
    void init(InitContext context);

    int getCD(Entity self);

    int getMAXCD();

    /**
     * 每回合调用，以减少CD。
     * 参考彼岸花花海，暂定回合开始时调用。
     *
     * @param self 自身。
     * @return 剩余CD。0表示可用。
     */
    int step(Entity self);
}
