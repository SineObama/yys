package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.JuanLiu;
import com.sine.yys.skill.commonattack.ShuiHuaDan;
import com.sine.yys.skill.passive.RunWuWuSheng;

import java.util.Arrays;
import java.util.Collection;

/**
 * 椒图。
 */
public class JiaoTu extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new ShuiHuaDan(), new RunWuWuSheng(), new JuanLiu());
    }

    @Override
    public double getOriginAttack() {
        return 2305;
    }

    @Override
    public String getName() {
        return "椒图";
    }
}
