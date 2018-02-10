package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.CommonAttackEvent;
import com.sine.yys.simulation.component.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.targetresolver.TargetResolver;
import com.sine.yys.simulation.model.Attack;
import com.sine.yys.simulation.model.AttackImpl;
import com.sine.yys.simulation.model.entity.Entity;

/**
 * 普攻通用逻辑。
 */
public abstract class BaseCommonAttack extends BaseSkill implements CommonAttack {
    @Override
    public int getFire() {
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
    public Attack getAttack() {
        return new AttackImpl(getCoefficient());
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    public final void apply(Controller controller) {
        doApply(controller.getTarget(), controller);
        // 触发普攻事件
        controller.getOwn().getEventController().trigger(CommonAttackEvent.class, new CommonAttackEvent(getSelf(), controller.getTarget()), controller);
    }

    @Override
    public void xieZhan(Entity target, Controller controller) {
        doApply(target, controller);
    }

    @Override
    public void doApply(Entity target, Controller controller) {
        for (int i = 0; i < getTimes(); i++) {
            if (!target.isAlive())
                break;
            controller.damage(getSelf(), target, getAttack());
        }
    }
}
