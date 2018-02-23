package com.sine.yys.simulation.component.shishen;

import com.sine.yys.simulation.component.shishen.skill.SanJian;
import com.sine.yys.simulation.component.shishen.skill.TianXiangHeZhan;
import com.sine.yys.simulation.component.shishen.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseShiShen {
    public GuHuoNiao() {
        super(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
