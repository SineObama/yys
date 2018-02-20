package com.sine.yys.simulation.component.core.shishen;

import com.sine.yys.simulation.component.core.skill.GuiXi;
import com.sine.yys.simulation.component.core.skill.GuiZhiJiaMian;
import com.sine.yys.simulation.component.core.skill.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends BaseShiShen {
    public BoRe() {
        super(Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
