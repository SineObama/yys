package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.event.UseFireEvent;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 青行灯-明灯。
 */
public class MingDeng extends BaseSkill implements PassiveSkill, EventHandler<UseFireEvent> {
    @Override
    public String getName() {
        return "明灯";
    }

    /**
     * 队友技能不耗火概率。
     */
    public double getPct() {
        return 0.14;
    }

    @Override
    public void handle(UseFireEvent event) {
        if (event.getSelf() != getSelf() && RandUtil.success(getPct())) {
            log.info(Msg.trigger(this));
            event.setCostFire(0);
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getOwn().getEventController().add(this);
    }
}
