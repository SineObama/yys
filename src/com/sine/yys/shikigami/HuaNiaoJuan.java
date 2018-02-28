package com.sine.yys.shikigami;

import com.sine.yys.skill.HuaNiaoXiangWen;
import com.sine.yys.skill.commonattack.GuiNiao;
import com.sine.yys.skill.commonattack.GuiZhiJiaMian;
import com.sine.yys.skill.groupattack.GuiXi;
import com.sine.yys.skill.passive.HuaJing;
import com.sine.yys.skill.passive.JiHenZhiXin;

import java.util.Arrays;

/**
 * 花鸟卷。
 */
public class HuaNiaoJuan extends BaseShikigami {
    public HuaNiaoJuan() {
        super(Arrays.asList(new GuiNiao(), new HuaJing(), new HuaNiaoXiangWen()), "花鸟卷");
    }
}
