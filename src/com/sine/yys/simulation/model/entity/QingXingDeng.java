package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.Property;
import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.MingDeng;
import com.sine.yys.simulation.model.skill.XiHunDeng;
import com.sine.yys.simulation.model.skill.YouGuang;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseEntity implements Shikigami {
    public QingXingDeng(Property property, Mitama mitama) {
        super(property, mitama, Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), "青行灯");
    }
}
