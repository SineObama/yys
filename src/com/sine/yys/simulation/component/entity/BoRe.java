package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.Shikigami;
import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.skill.GuiXi;
import com.sine.yys.simulation.component.skill.GuiZhiJiaMian;
import com.sine.yys.simulation.component.skill.JiHenZhiXin;
import com.sine.yys.simulation.info.Property;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends  Shikigami {
    public BoRe(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
