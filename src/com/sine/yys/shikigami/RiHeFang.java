package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.ZiYang;
import com.sine.yys.skill.commonattack.YangYan;
import com.sine.yys.skill.passive.QingYu;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日和坊。
 */
public class RiHeFang extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new YangYan(), new QingYu(), new ZiYang());
    }

    @Override
    public double getOriginAttack() {
        return 2358;
    }

    @Override
    public String getName() {
        return "日和坊";
    }
}
