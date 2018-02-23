package com.sine.yys.simulation.component.model.shishen;

import com.sine.yys.simulation.component.model.skill.SanJian;
import com.sine.yys.simulation.component.model.skill.TianXiangHeZhan;
import com.sine.yys.simulation.component.model.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseShiShen {
    public GuHuoNiao() {
        super(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
