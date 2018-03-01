package com.sine.yys.skill;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;

import java.util.Collection;

/**
 * 主动技能通用逻辑。
 * 固定了apply，执行特定函数以实现群体/多段攻击不重复计算。
 */
public abstract class BaseActiveSkill extends BaseSkill implements ActiveSkill {
    @Override
    public final void apply(Entity target) {
        if (getMAXCD() > 0)
           setCD(getMAXCD());
        beforeApply(target);
        doApply(target);
        afterApply(target);
    }

    /**
     * 技能的具体操作。各技能重写的重点。
     *
     * @param target 目标。
     */
    protected abstract void doApply(Entity target);

    /**
     * 获取攻击时附带的debuff效果。默认无效果。
     */
    protected Collection<DebuffEffect> getDebuffEffects() {
        return null;
    }

    /**
     * 目前只用于普攻触发事件。
     *
     * @param target 目标。
     */
    protected void beforeApply(Entity target) {
    }

    /**
     * 目前只用于普攻触发事件。
     *
     * @param target 目标。
     */
    protected void afterApply(Entity target) {
    }
}
