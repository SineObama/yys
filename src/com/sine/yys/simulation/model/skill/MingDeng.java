package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.component.event.UseFireEvent;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 明灯
 */
public class MingDeng extends BaseSkill implements PassiveSkill, EventHandler<UseFireEvent> {
    @Override
    public String getName() {
        return "明灯";
    }

    public double pct() {
        return 0.14;
    }

    @Override
    public void handle(UseFireEvent event) {
        if (RandUtil.success(pct())) {
            log.info(Msg.trigger(this));
            event.setCostFire(0);
        }
    }

    @Override
    public void init(InitContext context) {
        context.getOwn().getEventController().add(UseFireEvent.class, this);
    }
}
