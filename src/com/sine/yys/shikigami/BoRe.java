package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.commonattack.GuiZhiJiaMian;
import com.sine.yys.skill.groupattack.GuiXi;
import com.sine.yys.skill.passive.JiHenZhiXin;

import java.util.Arrays;
import java.util.Collection;

/**
 * 般若。
 */
public class BoRe extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi());
    }

    @Override
    public double getOriginAttack() {
        return 3136;
    }

    @Override
    public String getName() {
        return "般若";
    }
}
