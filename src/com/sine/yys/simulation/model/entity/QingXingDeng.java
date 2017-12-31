package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.skill.Skill;
import com.sine.yys.simulation.model.skill.XiHunDeng;
import com.sine.yys.simulation.model.skill.YouGuang;

import java.util.Arrays;
import java.util.List;

public class QingXingDeng extends BaseEntity {
    public QingXingDeng(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef) {
        super(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, Arrays.asList(new YouGuang(), new XiHunDeng()));
    }

    @Override
    public String getName() {
        return "青行灯";
    }
}
