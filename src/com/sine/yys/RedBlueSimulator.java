package com.sine.yys;

import com.sine.yys.entity.BattleKoinobori;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.impl.CampInfo;
import com.sine.yys.impl.EntityInfo;
import com.sine.yys.mitama.MitamaFactory;
import com.sine.yys.shikigami.ShikigamiFactory;
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
    private final Logger log = Logger.getLogger(getClass().getName());
    private final CampInfo redInfo, blueInfo;
    private final Map<String, Integer> count = new HashMap<>(2);
    private long start, end;

    public RedBlueSimulator(CampInfo red, CampInfo blue) {
        this.redInfo = red;
        this.blueInfo = blue;
        count.put(redName, 0);
        count.put(buleName, 0);
    }

    /**
     * 单线程模拟。
     *
     * @param times 总次数。
     */
    public void test(int times) {
        start = System.currentTimeMillis();
        new SimulateThread(times).run();
        end = System.currentTimeMillis();
        printResult();
    }

    /**
     * 多线程模拟。
     *
     * @param times  总次数。
     * @param thread 线程数。
     */
    public void test(int times, int thread) {
        start = System.currentTimeMillis();
        SimulateThread[] threads = new SimulateThread[thread];
        for (int i = 0; i < thread; i++) {
            threads[i] = new SimulateThread(times / thread + (times % thread + i) / thread);
            threads[i].start();
        }
        for (SimulateThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        printResult();
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

    class SimulateThread extends Thread {
        final int times;

        SimulateThread(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            final Map<String, Integer> count2 = new HashMap<>(2);
            count2.put(redName, 0);
            count2.put(buleName, 0);
            int i = 1;
            try {
                for (; i <= times; i++) {
                    BaseCamp red = new PVPCamp(redName, 3);
                    BaseCamp blue = new PVPCamp(buleName, 3);
                    for (EntityInfo info : redInfo.infos)
                        red.addEntity(new ShikigamiEntityImpl(ShikigamiFactory.create(info.shiShen), info.property, MitamaFactory.create(info.mitama), redInfo.lifeTimes, ShikigamiFactory.getDefaultName(info.shiShen)));
                    for (EntityInfo info : blueInfo.infos)
                        blue.addEntity(new ShikigamiEntityImpl(ShikigamiFactory.create(info.shiShen), info.property, MitamaFactory.create(info.mitama), blueInfo.lifeTimes, ShikigamiFactory.getDefaultName(info.shiShen)));
                    Simulator simulator = new Simulator(red, blue, Collections.singletonList(new BattleKoinobori(128.0, red, blue)));
                    while (simulator.step()) ;
                    String winName = simulator.getWin().getName();
                    log.info(Msg.join("测试", i, winName, "胜利"));
                    count2.put(winName, count2.get(winName) + 1);
                }
            } catch (Exception e) {
                log.severe(JSON.format("test case", i));
                throw e;
            }
            synchronized (count) {
                count.put(redName, count.get(redName) + count2.get(redName));
                count.put(buleName, count.get(buleName) + count2.get(buleName));
            }
        }
    }
}
