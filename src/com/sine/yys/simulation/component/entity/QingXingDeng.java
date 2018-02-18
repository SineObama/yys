package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.BaseEntity;
import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.skill.MingDeng;
import com.sine.yys.simulation.component.skill.XiHunDeng;
import com.sine.yys.simulation.component.skill.YouGuang;
import com.sine.yys.simulation.info.Property;
import com.sine.yys.simulation.component.model.Shikigami;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseEntity implements Shikigami {
    public QingXingDeng(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
