package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.entity.skill.SanJian;
import com.sine.yys.simulation.component.entity.skill.TianXiangHeZhan;
import com.sine.yys.simulation.component.entity.skill.XieZhan;
import com.sine.yys.simulation.component.model.Shikigami;
import com.sine.yys.simulation.info.Property;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseEntity implements Shikigami {
    public GuHuoNiao(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
