package com.sine.yys.skill.commonattack;

import com.sine.yys.attacktype.OriginAttackType;
import com.sine.yys.inter.CommonAttack;
import com.sine.yys.inter.Entity;
import com.sine.yys.skill.mono.BaseDirectiveSkill;

/**
 * 普通攻击。
 */
public abstract class BaseCommonAttack extends BaseDirectiveSkill implements CommonAttack {
    @Override
    public final int getFire() {
        return 0;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
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
    public final void counter(Entity target) {
        beforeApply(target);
        doCounter(target);
        afterApply(target);
    }

    protected void doCounter(Entity target) {
        for (int i = 0; i < getTimes(); i++) {
            final OriginAttackType attackType = new OriginAttackType(getSelf(), target, getAttack());
            attackType.setCounter();
            getController().attack(getSelf(), target, attackType, getDebuffEffects());
        }
    }
}
