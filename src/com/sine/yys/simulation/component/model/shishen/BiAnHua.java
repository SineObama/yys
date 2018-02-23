package com.sine.yys.simulation.component.model.shishen;

import com.sine.yys.simulation.component.model.shishen.skill.ChiTuanHua;
import com.sine.yys.simulation.component.model.shishen.skill.SiWangZhiHua;
import com.sine.yys.simulation.component.model.shishen.skill.XueZhiHuaHai;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseShiShen {
    public BiAnHua() {
        super(Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(), new XueZhiHuaHai()), "彼岸花");
    }
}
