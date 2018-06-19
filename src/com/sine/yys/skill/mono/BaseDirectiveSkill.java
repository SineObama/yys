package com.sine.yys.skill.mono;

import com.sine.yys.event.BeMonoAttackEvent;
import com.sine.yys.impl.OriginAttackType;
import com.sine.yys.inter.DirectiveSkill;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.ShikigamiEntity;
import com.sine.yys.inter.TargetResolver;
import com.sine.yys.skill.BaseAttackSkill;
import com.sine.yys.skill.JuanLiu;
import com.sine.yys.skill.targetresolver.EnemyEntityResolver;

/**
 * 指向性攻击技能。
 * 包括普攻和部分鬼火技能。
 * <p>
 * 在技能释放前触发对方的{@linkplain BeMonoAttackEvent 被单体攻击事件}。
 */
public abstract class BaseDirectiveSkill extends BaseAttackSkill implements DirectiveSkill {
    @Override
    protected void beforeApply(Entity target) {
        super.beforeApply(target);
        if (target instanceof ShikigamiEntity) {
            // 触发对方被单体攻击事件
            target.getEventController().trigger(new BeMonoAttackEvent((ShikigamiEntity) target, getSelf(), target.getBuffController().contain(JuanLiu.JuanLiuBuff.class)));
        }
    }

    /**
     * 默认以getAttack的攻击，对target攻击getTimes次。
     */
    @Override
    protected void doApply(Entity target) {
        for (int i = 0; i < getTimes(); i++)
            getController().attack(getSelf(), target, new OriginAttackType(getSelf(), target, getAttack()), getDebuffEffects());
    }

    /**
     * @return 攻击次数（段数）。
     */
    public int getTimes() {
        return 1;
    }

    /**
     * 目标选择为一个敌对单位。
     */
    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }
}
