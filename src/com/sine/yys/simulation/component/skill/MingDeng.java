package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.UseFireEvent;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 青行灯-明灯。
 */
public class MingDeng extends BasePassiveSkill implements PassiveSkill, EventHandler<UseFireEvent> {
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
            log.info(Msg.trigger(getSelf(), this));
            event.setCostFire(0);
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getOwn().getEventController().add(this);
    }
}
