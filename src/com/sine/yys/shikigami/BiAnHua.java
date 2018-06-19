package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.XueZhiHuaHai;
import com.sine.yys.skill.commonattack.SiWangZhiHua;
import com.sine.yys.skill.passive.ChiTuanHua;

import java.util.Arrays;
import java.util.Collection;

/**
 * 彼岸花。
 */
public class BiAnHua extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        final XueZhiHuaHai xueZhiHuaHai = new XueZhiHuaHai();
        return Arrays.asList(new SiWangZhiHua(), new ChiTuanHua(xueZhiHuaHai), xueZhiHuaHai);
    }

    @Override
    public double getOriginAttack() {
        return 3002;
    }

    @Override
    public String getName() {
        return "彼岸花";
    }
}
