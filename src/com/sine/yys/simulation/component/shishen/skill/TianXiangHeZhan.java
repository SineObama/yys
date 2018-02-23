package com.sine.yys.simulation.component.shishen.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.shishen.skill.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.shishen.skill.targetresolver.TargetResolver;
import com.sine.yys.simulation.info.AttackInfoImpl;

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
