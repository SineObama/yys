package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.commonattack.XueZou;
import com.sine.yys.skill.mono.LongYueXueHuaZhan;
import com.sine.yys.skill.passive.ShuangTianZhiZhi;

import java.util.Arrays;
import java.util.Collection;

/**
 * 雪童子。
 */
public class XueTongZi extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new XueZou(), new ShuangTianZhiZhi(), new LongYueXueHuaZhan());
    }

    @Override
    public double getOriginAttack() {
        return 3323;
    }

    @Override
    public String getName() {
        return "雪童子";
    }
}
