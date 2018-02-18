package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.model.ActiveSkill;
import com.sine.yys.simulation.model.Entity;
import com.sine.yys.simulation.model.entity.BaseEntity;

/**
 * 主动技能通用逻辑。
 * 固定了apply，执行特定函数以实现群体/多段攻击不重复计算。
 */
public abstract class BaseActiveSkill extends BaseSkill implements ActiveSkill {
    @Override
    public final void apply(Entity target0) {
        BaseEntity target = (BaseEntity) target0;
        getSelf().clear();
        beforeApply(target);
        doApply(target);
        afterApply(target);
    }

    /**
     * 技能的具体操作。各技能重写的重点。
     */
    protected abstract void doApply(BaseEntity target);

    /**
     * 目前只用于普攻触发事件。
     */
    protected void beforeApply(BaseEntity target) {
    }

    /**
     * 目前只用于普攻触发事件。
     */
    protected void afterApply(BaseEntity target) {
    }
}
