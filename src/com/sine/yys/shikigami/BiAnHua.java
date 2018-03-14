package com.sine.yys.shikigami;

import com.sine.yys.skill.XueZhiHuaHai;
import com.sine.yys.skill.commonattack.SiWangZhiHua;
import com.sine.yys.skill.passive.ChiTuanHua;

import java.util.Arrays;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseShikigami {
    public BiAnHua() {
        super(() -> {
            final XueZhiHuaHai xueZhiHuaHai = new XueZhiHuaHai();
            return Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(xueZhiHuaHai), xueZhiHuaHai);
        }, "彼岸花", 3002);
    }
}
