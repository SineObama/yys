package com.sine.yys.shikigami;

import com.sine.yys.skill.XiongDiZhiBan;
import com.sine.yys.skill.commonattack.PangZou;
import com.sine.yys.skill.passive.RenDuoShiZhong;

import java.util.Arrays;

/**
 * 镰鼬。
 */
public class LianYou extends BaseShikigami {
    public LianYou() {
        super(Arrays.asList(new PangZou(), new RenDuoShiZhong(), new XiongDiZhiBan()), "镰鼬");
    }
}
