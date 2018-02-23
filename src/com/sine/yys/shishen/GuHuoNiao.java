package com.sine.yys.shishen;

import com.sine.yys.skill.SanJian;
import com.sine.yys.skill.TianXiangHeZhan;
import com.sine.yys.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseShiShen {
    public GuHuoNiao() {
        super(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
