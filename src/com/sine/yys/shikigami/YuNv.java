package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.TianZhiLei;
import com.sine.yys.skill.commonattack.LeiZhu;
import com.sine.yys.skill.passive.JingHuaZhiYu;

import java.util.Arrays;
import java.util.Collection;

// XXXX AI。

/**
 * 雨女。
 */
public class YuNv extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new LeiZhu(), new JingHuaZhiYu(), new TianZhiLei());
    }

    @Override
    public double getOriginAttack() {
        return 2251;
    }

    @Override
    public String getName() {
        return "雨女";
    }
}
