package com.sine.yys.shikigami;

import com.sine.yys.skill.XueZhiHuaHai;
import com.sine.yys.skill.commonattack.SiWangZhiHua;
import com.sine.yys.skill.passive.ChiTuanHua;

import java.util.ArrayList;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseShikigami {
    public BiAnHua() {
        super(new ArrayList<>(), "彼岸花", 3002);
        skills.add(new SiWangZhiHua());
        final XueZhiHuaHai xueZhiHuaHai = new XueZhiHuaHai();
        skills.add(new ChiTuanHua(xueZhiHuaHai));
        skills.add(xueZhiHuaHai);
    }
}
