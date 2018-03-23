package com.sine.yys.shikigami;

import com.sine.yys.skill.TianZhiLei;
import com.sine.yys.skill.commonattack.LeiZhu;
import com.sine.yys.skill.passive.JingHuaZhiYu;

import java.util.Arrays;

// XXXX 雨女AI未确定。

/**
 * 雨女。
 */
public class YuNv extends BaseShikigami {
    public YuNv() {
        super(Arrays.asList(new LeiZhu(), new JingHuaZhiYu(), new TianZhiLei()), "雨女", 2251);
    }
}
