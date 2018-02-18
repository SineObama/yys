package com.sine.yys.simulation;

import com.sine.yys.simulation.info.Property;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.battle.PVPCamp;
import com.sine.yys.simulation.model.entity.GuHuoNiao;
import com.sine.yys.simulation.model.entity.QingXingDeng;
import com.sine.yys.simulation.model.mitama.HuoLing;
import com.sine.yys.simulation.model.mitama.MeiYao;
import com.sine.yys.simulation.model.mitama.ZhenNv;
import com.sine.yys.simulation.simulator.BattleSimulator;
import com.sine.yys.simulation.simulator.Simulator;

/**
 * 保存一些测试样例。
 */
public class TestCases {
    public static String redName = "红方";
    public static String buleName = "蓝方";

    /**
     * 一半胜率
     */
    public static Simulator test1() {
        final int attack = 1240;
        Camp red = new PVPCamp(redName, 3);
        Camp blue = new PVPCamp(buleName, 3);
        red.addEntity(new GuHuoNiao(new Property(attack, 10000, 400, 173, 1, 1.5, 0, 0), new ZhenNv()));
        red.addEntity(new QingXingDeng(new Property(attack, 10000, 400, 172, 0, 1.5, 0, 0), new HuoLing()));
        blue.addEntity(new QingXingDeng(new Property(attack, 10000, 400, 171, 0.8, 1.5, 3, 0), new MeiYao()));
        blue.addEntity(new GuHuoNiao(new Property(attack, 10000, 400, 170, 0.8, 1.5, 3, 0), new MeiYao()));
        return new BattleSimulator(red, blue);
    }
}
