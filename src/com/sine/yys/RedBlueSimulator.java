package com.sine.yys;

import com.sine.yys.entity.BattleKoinobori;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.mitama.MitamaFactory;
import com.sine.yys.shikigami.ShikigamiFactory;
import com.sine.yys.simulation.BaseCamp;
import com.sine.yys.simulation.PVPCamp;
import com.sine.yys.simulation.Simulator;
import com.sine.yys.util.JSON;
import com.sine.yys.util.Msg;
import com.sine.yys.util.Seed;

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
    public static final String blueName = "蓝方";
    private final Logger log = Logger.getLogger(getClass().getName());
    private final CampInfo redInfo, blueInfo;
    private final Map<String, Integer> count = new HashMap<>(2);
    private long start, end;

    public RedBlueSimulator(CampInfo red, CampInfo blue) {
        this.redInfo = red;
        this.blueInfo = blue;
        count.put(redName, 0);
        count.put(blueName, 0);
    }

    /**
     * 测试给定次数并输出结果。
     */
    public void test(int times) {
        int i = 1;
        try {
            start = System.currentTimeMillis();
            for (; i <= times; i++) {
                BaseCamp red = new PVPCamp(redName, 3);
                BaseCamp blue = new PVPCamp(blueName, 3);
                for (EntityInfo info : redInfo.infos)
                    red.addEntity(new ShikigamiEntityImpl(ShikigamiFactory.create(info.shiShen), info.property, MitamaFactory.create(info.mitama), redInfo.lifeTimes, ShikigamiFactory.getDefaultName(info.shiShen)));
                for (EntityInfo info : blueInfo.infos)
                    blue.addEntity(new ShikigamiEntityImpl(ShikigamiFactory.create(info.shiShen), info.property, MitamaFactory.create(info.mitama), blueInfo.lifeTimes, ShikigamiFactory.getDefaultName(info.shiShen)));
                Simulator simulator = new Simulator(red, blue, Collections.singletonList(new BattleKoinobori(128.0, red, blue)));
                while (simulator.step()) ;
                String winName = simulator.getWin().getName();
                log.info(Msg.join("测试", i, winName, "胜利"));
                count.put(winName, count.get(winName) + 1);
            }
            end = System.currentTimeMillis();
            printResult();
        } catch (Exception e) {
            log.severe("red:\n" + redInfo.lifeTimes + "\n" + redInfo.infosToString());
            log.severe("blue:\n" + blueInfo.lifeTimes + "\n" + blueInfo.infosToString());
            log.severe(JSON.format("test case", i, "seed", Seed.get()));
            throw e;
        }
    }

    public int getRedWins() {
        return count.get(redName);
    }

    public int getBlueWins() {
        return count.get(blueName);
    }

    public void printResult() {
        final String msg = "result=" + count.get(redName) + ":" + count.get(blueName) + ", time=" + (end - start);
        log.info(msg);
        System.out.println(msg);
    }
}
