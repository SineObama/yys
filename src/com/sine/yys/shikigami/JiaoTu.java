package com.sine.yys.shikigami;

import com.sine.yys.skill.JuanLiu;
import com.sine.yys.skill.commonattack.ShuiHuaDan;
import com.sine.yys.skill.passive.RunWuWuSheng;

import java.util.Arrays;

/**
 * 椒图。
 */
public class JiaoTu extends BaseShikigami {
    public JiaoTu() {
        super(Arrays.asList(new ShuiHuaDan(), new RunWuWuSheng(), new JuanLiu()), "椒图", 2305);
    }
}
