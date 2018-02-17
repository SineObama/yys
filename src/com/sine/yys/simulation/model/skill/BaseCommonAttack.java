package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.AttackInfo;
import com.sine.yys.simulation.model.AttackInfoImpl;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.event.CommonAttackEvent;

/**
 * 普攻通用逻辑。
 * 在普攻结束后触发普攻事件。
 */
public abstract class BaseCommonAttack extends BaseActiveSkill implements CommonAttack {
    @Override
    public final int getFire() {
        return 0;
    }

    public int getTimes() {
        return 1;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
    }

    @Override
    public AttackInfo getAttack() {
        return new AttackInfoImpl(getCoefficient());
    }

    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    public final void afterApply(Entity target) {
        // 触发普攻事件
        getSelf().getCamp().getEventController().trigger(new CommonAttackEvent(getSelf(), target));
    }

    @Override
    public void xieZhan(Entity target) {
        doApply(target);
    }

    @Override
    public void doApply(Entity target) {
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getSelf().attack(target, getAttack());
        }
    }
}
