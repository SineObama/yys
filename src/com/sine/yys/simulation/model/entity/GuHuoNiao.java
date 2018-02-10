package com.sine.yys.simulation.model.entity;

import com.sine.yys.simulation.model.mitama.Mitama;
import com.sine.yys.simulation.model.skill.SanJian;
import com.sine.yys.simulation.model.skill.TianXiangHeZhan;
import com.sine.yys.simulation.model.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends BaseEntity implements Shikigami {
    private GuHuoNiao(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, Mitama mitama) {
        super(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, mitama);
    }

    @Override
    public String getName() {
        return "姑获鸟";
    }

    public static GuHuoNiao create(int attack, int maxLife, int defense, double speed, double critical, double criticalDamage, double effectHit, double effectDef, Mitama mitama) {
        GuHuoNiao entity = new GuHuoNiao(attack, maxLife, defense, speed, critical, criticalDamage, effectHit, effectDef, mitama);
        entity.setSkills(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()));
        return entity;
    }
}
