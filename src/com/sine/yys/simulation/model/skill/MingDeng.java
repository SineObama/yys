package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.ContextAndRunner;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 明灯
 */
public class MingDeng extends BaseSkill implements PassiveSkill, EventHandler {
    @Override
    public String getName() {
        return "明灯";
    }

    public double pct() {
        return 0.14;
    }

    @Override
    public void handle(ContextAndRunner context) {
        if (RandUtil.success(pct())) {
            log.info(Msg.trigger(this));
            context.setCostFire(0);
        }
    }
}
