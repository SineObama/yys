package com.sine.yys.skill.groupattack;

import com.sine.yys.event.BeMonoAttackEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;

/**
 * 姑获鸟-天翔鹤斩。
 */
public class TianXiangHeZhan extends BaseGroupAttack {
    @Override
    public void doApply(Entity target) {
        super.doApply(target);
        getController().attack(getSelf(), target, getAttack(), getAttackType());
    }

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
    protected final void beforeApply(Entity target) {
        if (target instanceof ShikigamiEntity)
            target.getEventController().trigger(new BeMonoAttackEvent((ShikigamiEntity) target, getSelf()));
    }
}
