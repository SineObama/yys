package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.operation.SimpleCureHandler;
import com.sine.yys.skill.BaseSkill;
import com.sine.yys.skill.HuaNiaoXiangWen;
import com.sine.yys.skill.MingZhiXianJi;
import com.sine.yys.skill.commonattack.YuCi;
import com.sine.yys.skill.passive.YuYi;

import java.util.Arrays;
import java.util.Collection;

/**
 * 童女。
 */
public class TongNv extends BaseShikigami {
    @Override
    protected Collection<BaseSkill> initSkill() {
        return Arrays.asList(new YuCi(), new YuYi(), new MingZhiXianJi());
    }

    @Override
    public OperationHandler getAI() {
        return new SimpleCureHandler<>(HuaNiaoXiangWen.class, 0.6, false);
    }

    @Override
    public double getOriginAttack() {
        return 2412;
    }

    @Override
    public String getName() {
        return "童女";
    }
}
