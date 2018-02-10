package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.AttackImpl;

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
        return 0.33;
    }

    public double getFinalCoefficient() {
        return 0.88;
    }

    @Override
    public int getTimes() {
        return 3;
    }

    @Override
    public void apply(Controller controller) {
        super.apply(controller);
        controller.damage(new AttackImpl(getFinalCoefficient()));
    }
}
