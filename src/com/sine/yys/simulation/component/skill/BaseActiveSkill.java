package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Entity;

/**
 * 主动技能通用逻辑。
 * 固定了apply，执行特定函数以实现群体/多段攻击不重复计算。
 */
public abstract class BaseActiveSkill extends BaseSkill implements ActiveSkill {
    @Override
    public final void apply(Entity self, Entity target) {
        self.clear();
        self.put(this.getClass(), CD, getMAXCD() + 1);  // XXXX 设置技能进入CD，这个+1的操作
        beforeApply(target);
        doApply(self, target);
        afterApply(self, target);
    }

    /**
     * 技能的具体操作。各技能重写的重点。
     *
     * @param self   自身。
     * @param target 目标。
     */
    protected abstract void doApply(Entity self, Entity target);

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
     * @param self   自身
     * @param target 目标。
     */
    protected void afterApply(Entity self, Entity target) {
    }
}
