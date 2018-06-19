package com.sine.yys.shikigami;

import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.commonattack.YouGuang;
import com.sine.yys.skill.groupattack.XiHunDeng;
import com.sine.yys.skill.passive.MingDeng;

import java.util.Arrays;
import java.util.Collection;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng());
    }

    @Override
    public double getOriginAttack() {
        return 2439;
    }

    @Override
    public String getName() {
        return "青行灯";
    }
}
