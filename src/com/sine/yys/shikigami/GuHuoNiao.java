package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.commonattack.SanJian;
import com.sine.yys.skill.groupattack.TianXiangHeZhan;
import com.sine.yys.skill.passive.XieZhan;

import java.util.Arrays;
import java.util.Collection;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan());
    }

    @Override
    public double getOriginAttack() {
        return 3082;
    }

    @Override
    public String getName() {
        return "姑获鸟";
    }
}
