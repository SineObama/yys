package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.BattleStartEvent;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.shield.BangJingShield;
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
    public void handle(BattleStartEvent context, Controller controller) {
        log.info(Msg.trigger(this));
        getSelf().getCamp().addFire(getFire());
    }

    @Override
    public void init(InitContext context) {
        super.init(context);
        context.getOwn().getEventController().add(BattleStartEvent.class, this);
    }
}
