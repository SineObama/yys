package com.sine.yys.shishen;

import com.sine.yys.skill.passive.MingDeng;
import com.sine.yys.skill.groupattack.XiHunDeng;
import com.sine.yys.skill.commonattack.YouGuang;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseShiShen {
    public QingXingDeng() {
        super(Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
