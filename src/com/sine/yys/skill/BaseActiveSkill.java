package com.sine.yys.skill;

import com.sine.yys.event.UseFireEvent;
import com.sine.yys.inter.ActiveSkill;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

import java.util.Collection;

/**
 * 主动技能通用逻辑。
 */
public abstract class BaseActiveSkill extends BaseSkill implements ActiveSkill {
    @Override
    public final void apply(Entity target) {
        // 消耗鬼火
        int fire = getFire();
        if (fire > 0) {
            final Entity self = getSelf();
            UseFireEvent event = new UseFireEvent(self, fire);
            getOwn().getEventController().trigger(event);
            fire = event.getCostFire();
            self.getFireRepo().useFire(fire); // XXX 对于荒-月的逻辑修改
            log.info(Msg.info(self, "消耗", fire, "点鬼火，剩余", self.getFireRepo().getFire(), "点"));
        }

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
     * 目前没用。
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
