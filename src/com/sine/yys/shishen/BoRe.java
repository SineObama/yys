package com.sine.yys.shishen;

import com.sine.yys.skill.groupattack.GuiXi;
import com.sine.yys.skill.commonattack.GuiZhiJiaMian;
import com.sine.yys.skill.passive.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends BaseShiShen {
    public BoRe() {
        super(Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
