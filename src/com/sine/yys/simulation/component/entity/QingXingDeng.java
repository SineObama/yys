package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.Shikigami;
import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.skill.MingDeng;
import com.sine.yys.simulation.component.skill.XiHunDeng;
import com.sine.yys.simulation.component.skill.YouGuang;
import com.sine.yys.simulation.info.Property;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends  Shikigami {
    public QingXingDeng(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
