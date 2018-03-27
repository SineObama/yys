package com.sine.yys.shikigami;

import com.sine.yys.inter.OperationHandler;
import com.sine.yys.shikigami.operation.SimpleCureHandler;
import com.sine.yys.skill.HuaNiaoXiangWen;
import com.sine.yys.skill.MingZhiXianJi;
import com.sine.yys.skill.commonattack.YuCi;
import com.sine.yys.skill.passive.YuYi;

import java.util.Arrays;

/**
 * 童女。
 */
public class TongNv extends BaseShikigami {
    public TongNv() {
        super(Arrays.asList(new YuCi(), new YuYi(), new MingZhiXianJi()), "童女", 2412);
    }

    @Override
    public OperationHandler getAI() {
        return new SimpleCureHandler<>(HuaNiaoXiangWen.class, 0.6, false);
    }
}
