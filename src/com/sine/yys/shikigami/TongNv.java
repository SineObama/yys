package com.sine.yys.shikigami;

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
}
