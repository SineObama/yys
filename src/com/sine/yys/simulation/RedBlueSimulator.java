package com.sine.yys.simulation;

import com.sine.yys.simulation.component.BattleKoinobori;
import com.sine.yys.simulation.component.Camp;
import com.sine.yys.simulation.component.Shikigami;
import com.sine.yys.simulation.component.Simulator;
import com.sine.yys.simulation.component.camp.PVPCamp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 模拟对弈竞猜，计算多次结果。
 */
public class RedBlueSimulator {
    public static final String redName = "红方";
    public static final String buleName = "蓝方";
    private final Logger log = Logger.getLogger(getClass().toString());
    private final CampInfo redInfo, blueInfo;
    private final Map<String, Integer> count = new HashMap<>(2);

    public RedBlueSimulator(CampInfo red, CampInfo blue) {
        this.redInfo = red;
        this.blueInfo = blue;
        count.put(redName, 0);
        count.put(buleName, 0);
    }

    public void test(int times) {
        for (int i = 0; i < times; i++) {
            Camp red = new PVPCamp(redName, 3);
            Camp blue = new PVPCamp(buleName, 3);
            for (EntityInfo info : redInfo.infos) {
                red.addEntity(new Shikigami(info.property, info.mitama, info.shiShen));
            }
            for (EntityInfo info : blueInfo.infos) {
                blue.addEntity(new Shikigami(info.property, info.mitama, info.shiShen));
            }
            Simulator simulator = new Simulator(red, blue, Collections.singletonList(new BattleKoinobori(128.0)));
            while (simulator.getWin() == null)
                simulator.step();
            String winName = simulator.getWin().getName();
            log.warning(winName + " 胜利");
            count.put(winName, count.get(winName) + 1);
        }
        log.severe(count.get(redName) + ":" + count.get(buleName));
    }

    public int getRedWins() {
        return count.get(redName);
    }

    public int getBlueWins() {
        return count.get(buleName);
    }
}
