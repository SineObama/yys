package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.skill.ChiTuanHua;
import com.sine.yys.simulation.component.skill.SiWangZhiHua;
import com.sine.yys.simulation.component.skill.XueZhiHuaHai;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends ShiShen {
    public BiAnHua() {
        super(Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(), new XueZhiHuaHai()), "彼岸花");
    }
}
