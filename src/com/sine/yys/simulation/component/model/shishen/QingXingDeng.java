package com.sine.yys.simulation.component.model.shishen;

import com.sine.yys.simulation.component.model.shishen.skill.MingDeng;
import com.sine.yys.simulation.component.model.shishen.skill.XiHunDeng;
import com.sine.yys.simulation.component.model.shishen.skill.YouGuang;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseShiShen {
    public QingXingDeng() {
        super(Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
