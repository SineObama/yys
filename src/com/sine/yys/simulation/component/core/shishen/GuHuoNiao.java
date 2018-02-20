package com.sine.yys.simulation.component.core.shishen;

import com.sine.yys.simulation.component.core.skill.SanJian;
import com.sine.yys.simulation.component.core.skill.TianXiangHeZhan;
import com.sine.yys.simulation.component.core.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseShiShen {
    public GuHuoNiao() {
        super(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
