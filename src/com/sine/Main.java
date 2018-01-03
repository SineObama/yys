package com.sine;

import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.CampImpl;
import com.sine.yys.simulation.model.entity.QingXingDeng;
import com.sine.yys.simulation.simulator.BattleSimulator;
import com.sine.yys.simulation.simulator.Simulator;

import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.toString());

    public static void main(String[] args) {
        final int times = 100;
        int redWin = 0, blueWin = 0;
        final int attack = 1240;
        for (int i = 0; i < times; i++) {
            Camp red = new CampImpl("红方", 3);
            red.addEntity(new QingXingDeng(attack, 10000, 400, 170, 0.08, 1.5, 0, 0));
            red.addEntity(new QingXingDeng(attack, 10000, 400, 170, 0.08, 1.5, 0, 0));
            Camp blue = new CampImpl("蓝方", 3);
            blue.addEntity(new QingXingDeng(attack, 10000, 400, 100, 0.08, 1.5, 0, 0));
            blue.addEntity(new QingXingDeng(attack, 10000, 400, 100, 0.08, 1.5, 0, 0));
            blue.addEntity(new QingXingDeng(attack, 10000, 400, 100, 0.08, 1.5, 0, 0));
            Simulator simulator = new BattleSimulator(red, blue);
            Camp win;
            while ((win = simulator.step()) == null) ;
            log.warning(win.getName() + "胜利");
            if (win == red)
                redWin += 1;
            else
                blueWin += 1;
        }
        log.severe(redWin + ":" + blueWin);
    }

}
