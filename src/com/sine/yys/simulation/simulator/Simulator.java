package com.sine.yys.simulation.simulator;

import com.sine.yys.simulation.component.SpeedBar;
import com.sine.yys.simulation.component.operationhandler.OperationHandler;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.entity.BattleKoinobori;

/**
 * 模拟器接口。
 * 包含2个阵营。
 */
public interface Simulator {
    /**
     * 行动一步（己方或敌方）
     *
     * @return 胜利阵营，未胜利则null。
     */
    Camp step();

    Camp getCamp0();

    Camp getCamp1();

    /**
     * @return 战场鲤鱼旗
     */
    BattleKoinobori getBattleKoinobori();

    boolean isAuto();

    void setAuto(boolean auto);

    OperationHandler getHandler();

    void setHandler(OperationHandler handler);

    SpeedBar getSpeedBar();

    boolean isStarted();

    int getRound();
}
