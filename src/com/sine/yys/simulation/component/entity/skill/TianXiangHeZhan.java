package com.sine.yys.simulation.component.entity.skill;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.TargetResolver;
import com.sine.yys.simulation.component.entity.targetresolver.EnemyEntityResolver;
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
    public void doApply(Entity target) {
        super.doApply(target);
        getSelf().attack(target, new AttackInfoImpl(getFinalCoefficient()));
    }
}
