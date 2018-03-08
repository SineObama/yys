package com.sine.yys.shikigami;

import com.sine.yys.skill.commonattack.GuiZhiJiaMian;
import com.sine.yys.skill.groupattack.GuiXi;
import com.sine.yys.skill.passive.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends BaseShikigami {
    public BoRe() {
        super(Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若", 3136);
    }
}
