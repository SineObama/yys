package com.sine.yys.simulation.component.core.shishen;

import com.sine.yys.simulation.component.core.skill.ChiTuanHua;
import com.sine.yys.simulation.component.core.skill.SiWangZhiHua;
import com.sine.yys.simulation.component.core.skill.XueZhiHuaHai;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseShiShen {
    public BiAnHua() {
        super(Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(), new XueZhiHuaHai()), "彼岸花");
    }
}
