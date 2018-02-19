package com.sine.yys.simulation.component.entity.skill;

import com.sine.yys.simulation.component.ActiveSkill;
import com.sine.yys.simulation.component.Entity;

/**
 * 主动技能通用逻辑。
 * 固定了apply，执行特定函数以实现群体/多段攻击不重复计算。
 */
public abstract class BaseActiveSkill extends BaseSkill implements ActiveSkill {
    @Override
    public final void apply(Entity target) {
        getSelf().clear();
        beforeApply(target);
        doApply(target);
        afterApply(target);
    }

    /**
     * 技能的具体操作。各技能重写的重点。
     *
     * @param target
     */
    protected abstract void doApply(Entity target);

    /**
     * 目前只用于普攻触发事件。
     *
     * @param target
     */
    protected void beforeApply(Entity target) {
    }

    /**
     * 目前只用于普攻触发事件。
     *
     * @param target
     */
    protected void afterApply(Entity target) {
    }
}
