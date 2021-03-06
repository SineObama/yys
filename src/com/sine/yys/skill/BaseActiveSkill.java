package com.sine.yys.skill;

import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.Entity;

/**
 * 主动技能。
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
     * 技能的具体操作，由具体技能定义。
     */
    protected abstract void doApply(Entity target);

    /**
     * 主动技能使用前调用。
     */
    protected void beforeApply(Entity target) {
    }

    /**
     * 主动技能使用后调用。
     */
    protected void afterApply(Entity target) {
    }
}
