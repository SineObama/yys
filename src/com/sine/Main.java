package com.sine;

import com.sine.yys.simulation.TestCases;
import com.sine.yys.simulation.info.Property;
import com.sine.yys.simulation.model.Camp;
import com.sine.yys.simulation.model.PVPCamp;
import com.sine.yys.simulation.model.entity.GuHuoNiao;
import com.sine.yys.simulation.model.entity.HuiYeJi;
import com.sine.yys.simulation.model.entity.QingXingDeng;
import com.sine.yys.simulation.model.mitama.DiZangXiang;
import com.sine.yys.simulation.model.mitama.HuoLing;
import com.sine.yys.simulation.model.mitama.MeiYao;
import com.sine.yys.simulation.model.mitama.ZhenNv;
import com.sine.yys.simulation.simulator.BattleSimulator;
import com.sine.yys.simulation.simulator.Simulator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    private static final Logger log;

    static {
        System.setProperty("java.util.logging.config.file", "resource/logging.properties");
        log = Logger.getLogger(Main.class.toString());
    }

    public static void main(String[] args) {
        final int times = 10;
        Map<String, Integer> count = new HashMap<>(2);
        count.put(TestCases.redName, 0);
        count.put(TestCases.buleName, 0);
        final int attack = 1240;
        for (int i = 0; i < times; i++) {
            Camp red = new PVPCamp(TestCases.redName, 3);
            Camp blue = new PVPCamp(TestCases.buleName, 3);
            red.addEntity(new GuHuoNiao(new Property(attack, 10000, 400, 170, 1, 1.5, 0, 0), new ZhenNv()));
            red.addEntity(new HuiYeJi(new Property(attack, 10000, 400, 170, 0, 1.5, 0, 0), new HuoLing()));
            blue.addEntity(new QingXingDeng(new Property(attack, 10000, 400, 170, 0.8, 1.5, 3, 0), new DiZangXiang()));
            blue.addEntity(new GuHuoNiao(new Property(attack, 10000, 400, 170, 0.8, 1.5, 3, 0), new MeiYao()));
            Simulator simulator = new BattleSimulator(red, blue);
            Camp win;
            while ((win = simulator.step()) == null) ;
            log.warning(win.getName() + " 胜利");
            count.put(win.getName(), count.get(win.getName()) + 1);
        }
        log.severe(count.get(TestCases.redName) + ":" + count.get(TestCases.buleName));
    }
}
