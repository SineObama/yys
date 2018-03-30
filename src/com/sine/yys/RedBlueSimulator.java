package com.sine.yys;

import com.sine.yys.entity.BattleKoinobori;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.simulation.BaseCamp;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import com.sine.yys.util.JSON;
import com.sine.yys.util.Msg;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 红蓝（对弈）竞猜模拟器。
 * <p>
 * 提供多次模拟、获取结果等方法。
 */
public class RedBlueSimulator {
    public static final String redName = "红方";
    public static final String buleName = "蓝方";
    private final double lifeTimes;
    private final Logger log = Logger.getLogger(getClass().getName());
    private final CampInfo redInfo, blueInfo;
    private final Map<String, Integer> count = new HashMap<>(2);
    private long start, end;

    public RedBlueSimulator(CampInfo red, CampInfo blue, double lifeTimes) {
        this.redInfo = red;
        this.blueInfo = blue;
        this.lifeTimes = lifeTimes;
        count.put(redName, 0);
        count.put(buleName, 0);
    }

    public void test(int times) {
        int i = 1;
        try {
            start = System.currentTimeMillis();
            for (; i <= times; i++) {
                BaseCamp red = new PVPCamp(redName, 3);
                BaseCamp blue = new PVPCamp(buleName, 3);
                for (EntityInfo info : redInfo.infos) {
                    red.addEntity(new ShikigamiEntityImpl(info.property, MitamaFactory.create(info.mitama), ShikigamiFactory.create(info.shiShen), lifeTimes));
                }
                for (EntityInfo info : blueInfo.infos) {
                    blue.addEntity(new ShikigamiEntityImpl(info.property, MitamaFactory.create(info.mitama), ShikigamiFactory.create(info.shiShen), lifeTimes));
                }
                Simulator simulator = new Simulator(red, blue, Collections.singletonList(new BattleKoinobori(128.0, red, blue)));
                while (simulator.getWin() == null)
                    simulator.step();
                String winName = simulator.getWin().getName();
                log.info(Msg.join("测试", i, winName, "胜利"));
                count.put(winName, count.get(winName) + 1);
            }
            end = System.currentTimeMillis();
            printResult();
        } catch (Exception e) {
            log.severe(JSON.format("test case", i));
            throw e;
        }
    }

    public int getRedWins() {
        return count.get(redName);
    }

    public int getBlueWins() {
        return count.get(buleName);
    }

    public void printResult() {
        final String msg = "result=" + count.get(redName) + ":" + count.get(buleName) + ", time=" + (end - start);
        log.info(msg);
        System.out.println(msg);
    }
}
