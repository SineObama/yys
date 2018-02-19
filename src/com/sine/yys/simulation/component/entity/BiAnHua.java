package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.Shikigami;
import com.sine.yys.simulation.component.mitama.Mitama;
import com.sine.yys.simulation.component.skill.ChiTuanHua;
import com.sine.yys.simulation.component.skill.SiWangZhiHua;
import com.sine.yys.simulation.component.skill.XueZhiHuaHai;
import com.sine.yys.simulation.info.Property;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseEntity implements Shikigami {
    public BiAnHua(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(), new XueZhiHuaHai()), "彼岸花");
    }
}
