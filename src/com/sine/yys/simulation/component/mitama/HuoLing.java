package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.InitContext;
import com.sine.yys.simulation.component.model.event.BattleStartEvent;
import com.sine.yys.simulation.util.Msg;

/**
 * 火灵。
 */
public class HuoLing extends BaseMitama implements Mitama, EventHandler<BattleStartEvent> {
    @Override
    public String getName() {
        return "火灵";
    }

    public int getFire() {
        return 3;
    }

    @Override
    public void handle(BattleStartEvent event) {
        log.info(Msg.trigger(getSelf(), this));
        getSelf().getFireRepo().addFire(getFire());
    }

    @Override
    public void doInit(InitContext context) {
        context.getOwn().getEventController().add(this);
    }
}
