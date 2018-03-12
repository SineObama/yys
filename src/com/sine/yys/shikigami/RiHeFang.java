package com.sine.yys.shikigami;

import com.sine.yys.skill.ZiYang;
import com.sine.yys.skill.commonattack.YangYan;
import com.sine.yys.skill.passive.QingYu;

import java.util.Arrays;

/**
 * 日和坊。
 */
public class RiHeFang extends BaseShikigami {
    public RiHeFang() {
        super(Arrays.asList(new YangYan(), new QingYu(), new ZiYang()), "日和坊", 2358);
    }
}
