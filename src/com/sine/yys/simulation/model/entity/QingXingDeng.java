package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.MingDeng;
import com.sine.yys.simulation.model.skill.XiHunDeng;
import com.sine.yys.simulation.model.skill.YouGuang;

import java.util.Arrays;

/**
 * 青行灯。
 */
public class QingXingDeng extends BaseEntity implements Shikigami {
    private QingXingDeng(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, Mitama mitama) {
        super(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, mitama);
    }

    @Override
    public String getName() {
        return "青行灯";
    }

    public static QingXingDeng create(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, Mitama mitama) {
        QingXingDeng entity = new QingXingDeng(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, mitama);
        entity.setSkills(Arrays.asList(new YouGuang(), new MingDeng(), new XiHunDeng()));
        return entity;
    }
}
