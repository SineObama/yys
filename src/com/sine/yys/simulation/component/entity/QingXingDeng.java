package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.skill.MingDeng;
import com.sine.yys.simulation.component.skill.XiHunDeng;
import com.sine.yys.simulation.component.skill.YouGuang;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends ShiShen {
    public QingXingDeng() {
        super(Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
