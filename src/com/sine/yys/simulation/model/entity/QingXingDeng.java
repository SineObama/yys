package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.skill.MingDeng;
import com.sine.yys.simulation.model.skill.XiHunDeng;
import com.sine.yys.simulation.model.skill.YouGuang;

import java.util.Arrays;

public class QingXingDeng extends BaseEntity implements Shikigami {
    public QingXingDeng(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef) {
        super(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()));
    }

    @Override
    public String getName() {
        return "青行灯";
    }
}
