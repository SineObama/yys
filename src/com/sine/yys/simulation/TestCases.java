package com.sine.yys.simulation;

import com.sine.yys.mitama.DiZangXiang;
import com.sine.yys.mitama.HuoLing;
import com.sine.yys.mitama.MeiYao;
import com.sine.yys.mitama.ZhenNv;
import com.sine.yys.shikigami.*;
import com.sine.yys.simulation.component.PropertyImpl;

/**
 * 保存一些测试样例。
 */
public class TestCases {

    /**
     * 一半胜率
     */
    public static RedBlueSimulator test1() {
        final int attack = 1240;
        CampInfo red = new CampInfo();
        CampInfo blue = new CampInfo();
        red.add(new GuHuoNiao(), new PropertyImpl(attack, 10000, 400, 173, 1, 1.5, 0, 0), new ZhenNv());
        red.add(new QingXingDeng(), new PropertyImpl(attack, 10000, 400, 172, 0, 1.5, 0, 0), new HuoLing());
        blue.add(new QingXingDeng(), new PropertyImpl(attack, 10000, 400, 171, 0.8, 1.5, 3, 0), new MeiYao());
        blue.add(new GuHuoNiao(), new PropertyImpl(attack, 10000, 400, 170, 0.8, 1.5, 3, 0), new MeiYao());
        return new RedBlueSimulator(red, blue);
    }

    /**
     * 一半胜率
     */
    public static RedBlueSimulator test2() {
        final int attack = 1240;
        CampInfo red = new CampInfo();
        CampInfo blue = new CampInfo();
        red.add(new BiAnHua(), new PropertyImpl(attack, 10000, 400, 173, 1, 1.5, 0, 0), new ZhenNv());
        red.add(new HuiYeJi(), new PropertyImpl(attack, 10000, 400, 172, 0, 1.5, 0, 0), new HuoLing());
        blue.add(new BoRe(), new PropertyImpl(attack, 10000, 400, 171, 0.8, 1.5, 0.3, 0), new DiZangXiang());  // 效果命中影响大
        blue.add(new GuHuoNiao(), new PropertyImpl(attack, 10000, 400, 170, 0.8, 1.5, 3, 0), new MeiYao());
        return new RedBlueSimulator(red, blue);
    }
}
