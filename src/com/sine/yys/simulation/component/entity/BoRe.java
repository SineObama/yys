package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.skill.GuiXi;
import com.sine.yys.simulation.component.skill.GuiZhiJiaMian;
import com.sine.yys.simulation.component.skill.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends ShiShen {
    public BoRe() {
        super(Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
