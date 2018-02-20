package com.sine;

import com.sine.yys.simulation.CampInfo;
import com.sine.yys.simulation.RedBlueSimulator;
import com.sine.yys.simulation.component.mitama.DiZangXiang;
import com.sine.yys.simulation.component.mitama.HuoLing;
import com.sine.yys.simulation.component.mitama.MeiYao;
import com.sine.yys.simulation.component.mitama.ZhenNv;
import com.sine.yys.simulation.component.shishen.BiAnHua;
import com.sine.yys.simulation.component.shishen.BoRe;
import com.sine.yys.simulation.component.shishen.GuHuoNiao;
import com.sine.yys.simulation.component.shishen.HuiYeJi;
import com.sine.yys.simulation.info.Property;

public class Main {
    static {
        System.setProperty("java.util.logging.config.file", "resource/logging.properties");
    }

    public static void main(String[] args) {
        final int times = 10;
        final int attack = 1240;
        CampInfo red = new CampInfo();
        CampInfo blue = new CampInfo();
        red.add(new BiAnHua(), new Property(attack, 10000, 400, 173, 1, 1.5, 0, 0), new ZhenNv());
        red.add(new HuiYeJi(), new Property(attack, 10000, 400, 172, 0, 1.5, 0, 0), new HuoLing());
        blue.add(new BoRe(), new Property(attack, 10000, 400, 171, 0.8, 1.5, 3, 0), new DiZangXiang());
        blue.add(new GuHuoNiao(), new Property(attack, 10000, 400, 170, 0.8, 1.5, 3, 0), new MeiYao());
        RedBlueSimulator simulator1 = new RedBlueSimulator(red, blue);
        simulator1.test(times);
    }
}
