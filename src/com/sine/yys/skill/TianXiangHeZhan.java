package com.sine.yys.skill;

import com.sine.yys.info.AttackInfoImpl;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;
import com.sine.yys.skill.targetresolver.TargetResolver;

/**
 * 姑获鸟-天翔鹤斩。
 */
public class TianXiangHeZhan extends SimpleGroupAttack {
    @Override
    public String getName() {
        return "天翔鹤斩";
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
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
    public void doApply(Controller controller, Entity self, Entity target) {
        super.doApply(controller, self, target);
        controller.attack(target, new AttackInfoImpl(getFinalCoefficient()));
    }
}
