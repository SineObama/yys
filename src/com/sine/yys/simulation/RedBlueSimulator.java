package com.sine.yys.simulation;

import com.sine.yys.simulation.component.*;
import com.sine.yys.util.Msg;

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
    private final Logger log = Logger.getLogger(getClass().getName());
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
            BaseCamp red = new PVPCamp(redName, 3);
            BaseCamp blue = new PVPCamp(buleName, 3);
            for (EntityInfo info : redInfo.infos) {
                red.addEntity(new ShikigamiEntityImpl(info.property, MitamaFactory.create(info.mitama), ShikigamiFactory.create(info.shiShen)));
            }
            for (EntityInfo info : blueInfo.infos) {
                blue.addEntity(new ShikigamiEntityImpl(info.property, MitamaFactory.create(info.mitama), ShikigamiFactory.create(info.shiShen)));
            }
            Simulator simulator = new Simulator(red, blue, Collections.singletonList(new BattleKoinobori(128.0)));
            while (simulator.getWin() == null)
                simulator.step();
            String winName = simulator.getWin().getName();
            log.info(Msg.join("测试", (i + 1), winName, "胜利"));
            count.put(winName, count.get(winName) + 1);
        }
        printResult();
    }

    public int getRedWins() {
        return count.get(redName);
    }

    public int getBlueWins() {
        return count.get(buleName);
    }

    public void printResult() {
        final String msg = "result=" + count.get(redName) + ":" + count.get(buleName);
        log.info(msg);
        System.out.println(msg);
    }
}
