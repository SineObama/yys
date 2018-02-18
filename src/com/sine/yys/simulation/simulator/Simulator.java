package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.model.Camp;

/**
 * 模拟器接口。
 * 包含2个阵营。
 */
public interface Simulator {
    /**
     * 行动一步（一回合，己方或敌方行动）。
     *
     * @return 胜利阵营，未胜利则null。
     */
    Camp step();

    /**
     * 双方总共回合数。
     */
    int getRound();
}
