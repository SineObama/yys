package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.entity.Entity;

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
     * 技能实际逻辑，各技能重写的重点。
     * 其中普通不触发普攻。
     */
    public abstract void doApply(Entity target);

    /**
     * 目前只用于普攻触发事件。
     */
    protected void beforeApply(Entity target) {
    }

    /**
     * 目前只用于普攻触发事件。
     */
    protected void afterApply(Entity target) {
    }
}
