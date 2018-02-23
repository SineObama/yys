package com.sine.yys.shishen;

import com.sine.yys.skill.GuiXi;
import com.sine.yys.skill.GuiZhiJiaMian;
import com.sine.yys.skill.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends BaseShiShen {
    public BoRe() {
        super(Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
