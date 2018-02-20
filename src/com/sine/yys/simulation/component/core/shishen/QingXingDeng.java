package com.sine.yys.simulation.component.core.shishen;

import com.sine.yys.simulation.component.core.skill.MingDeng;
import com.sine.yys.simulation.component.core.skill.XiHunDeng;
import com.sine.yys.simulation.component.core.skill.YouGuang;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseShiShen {
    public QingXingDeng() {
        super(Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
