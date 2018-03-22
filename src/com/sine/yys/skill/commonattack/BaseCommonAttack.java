package com.sine.yys.skill.commonattack;

import com.sine.yys.event.BeMonoAttackEvent;
import com.sine.yys.event.CommonAttackEvent;
import com.sine.yys.inter.CommonAttack;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.BaseAttackSkill;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;

/**
 * 普通攻击。
 * <p>
 * 结束后触发普攻事件。
 */
public abstract class BaseCommonAttack extends BaseAttackSkill implements CommonAttack {
    /**
     * 默认以getAttack的攻击，对target攻击getTimes次。
     */
    @Override
    protected void doApply(Entity target) {
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getController().attack(getSelf(), target, getAttack());
        }
    }

    @Override
    public final int getFire() {
        return 0;
    }

    /**
     * @return 攻击次数（段数）。
     */
    public int getTimes() {
        return 1;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
    }

    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    protected void beforeApply(Entity target) {
        // 触发对方被单体攻击事件（混乱打自己人不触发）
        if (getEnemy().contain(target) && target instanceof ShikigamiEntity) {
            getEnemy().getEventController().trigger(new BeMonoAttackEvent((ShikigamiEntity) target, getSelf()));
        }
    }

    @Override
    public final void afterApply(Entity target) {
        // 触发普攻事件
        getOwn().getEventController().trigger(new CommonAttackEvent(getSelf(), target));
    }

    /**
     * 协战。
     * <p>
     * 默认调用doApply。具体技能根据需要重写。
     */
    @Override
    public void xieZhan(Entity target) {
        doApply(target);
    }

    @Override
    public void counter(Entity target) {
        for (int i = 0; i < getTimes(); i++) {
            if (target.isDead())
                break;
            getController().counter(getSelf(), target, getAttack());
        }
    }
}
