package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.MingDeng;
import com.sine.yys.simulation.model.skill.XiHunDeng;
import com.sine.yys.simulation.model.skill.YouGuang;

import java.util.Arrays;

public class QingXingDeng extends BaseEntity implements Shikigami {
    public QingXingDeng(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, Mitama mitama) {
        super(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()), mitama);
    }

    @Override
    public String getName() {
        return "青行灯";
    }
}
