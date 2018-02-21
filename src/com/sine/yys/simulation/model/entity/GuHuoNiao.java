package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.Property;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.SanJian;
import com.sine.yys.simulation.model.skill.TianXiangHeZhan;
import com.sine.yys.simulation.model.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseEntity implements Shikigami {
    public GuHuoNiao(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
