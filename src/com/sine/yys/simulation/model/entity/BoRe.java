package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.Property;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.GuiXi;
import com.sine.yys.simulation.model.skill.GuiZhiJiaMian;
import com.sine.yys.simulation.model.skill.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends BaseEntity implements Shikigami {
    public BoRe(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
