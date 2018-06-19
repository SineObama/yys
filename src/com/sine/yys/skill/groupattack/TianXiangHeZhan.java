package com.sine.yys.skill.groupattack;

import com.sine.yys.attacktype.OriginAttackType;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.skill.model.AttackInfoImpl;
import com.sine.yys.skill.mono.BaseDirectiveSkill;

/**
 * 姑获鸟-天翔鹤斩。
 */
public class TianXiangHeZhan extends BaseDirectiveSkill {
    @Override
    public void doApply(Entity target) {
        final Controller controller = getController();
        final Entity self = getSelf();
        for (int i = 0; i < getTimes(); i++) {
            for (Entity entity : getEnemy().getAllAlive()) {
                controller.attack(self, entity, new OriginAttackType(self, entity, new AttackInfoImpl(getCoefficient())), getDebuffEffects());
            }
        }
        controller.attack(self, target, new OriginAttackType(self, target, new AttackInfoImpl(getFinalCoefficient())), getDebuffEffects());
    }

    @Override
    public String getName() {
        return "天翔鹤斩";
    }

    @Override
    public double getCoefficient() {
        return 0.33 * 1.24;
    }

    public double getFinalCoefficient() {
        return 0.88 * 1.24;
    }

    @Override
    public int getTimes() {
        return 3;
    }

    @Override
    public int getFire() {
        return 3;
    }
}
