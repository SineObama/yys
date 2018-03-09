package com.sine.yys.shikigami;

import com.sine.yys.skill.commonattack.SanJian;
import com.sine.yys.skill.groupattack.TianXiangHeZhan;
import com.sine.yys.skill.passive.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseShikigami {
    public GuHuoNiao() {
        super(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟", 3082);
    }
}
