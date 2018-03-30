package com.sine.yys.shikigami;

import com.sine.yys.skill.commonattack.XueZou;
import com.sine.yys.skill.mono.LongYueXueHuaZhan;
import com.sine.yys.skill.passive.ShuangTianZhiZhi;

import java.util.Arrays;

/**
 * 雪童子。
 */
public class XueTongZi extends BaseShikigami {
    public XueTongZi() {
        super(Arrays.asList(new XueZou(), new ShuangTianZhiZhi(), new LongYueXueHuaZhan()), "雪童子", 3323);
    }
}
