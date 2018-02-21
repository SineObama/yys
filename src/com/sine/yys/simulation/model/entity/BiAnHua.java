package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.Property;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.ChiTuanHua;
import com.sine.yys.simulation.model.skill.SiWangZhiHua;
import com.sine.yys.simulation.model.skill.XueZhiHuaHai;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseEntity implements Shikigami {
    public BiAnHua(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(), new XueZhiHuaHai()), "彼岸花");
    }
}
