package com.sine.yys.shishen;

import com.sine.yys.skill.passive.ChiTuanHua;
import com.sine.yys.skill.commonattack.SiWangZhiHua;
import com.sine.yys.skill.XueZhiHuaHai;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseShiShen {
    public BiAnHua() {
        super(Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(), new XueZhiHuaHai()), "彼岸花");
    }
}
