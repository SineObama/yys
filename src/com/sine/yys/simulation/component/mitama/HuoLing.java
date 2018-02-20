package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.BattleStartEvent;
import com.sine.yys.simulation.util.Msg;

/**
 * 火灵。
 */
public class HuoLing extends BaseMitama implements Mitama {
    @Override
    public String getName() {
        return "火灵";
    }

    public int getFire() {
        return 3;
    }

    @Override
    public void init(InitContext context) {
        context.getOwn().getEventController().add(new Handler(context.getSelf()));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<BattleStartEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(BattleStartEvent event) {
            log.info(Msg.trigger(self, HuoLing.this));
            self.getFireRepo().addFire(getFire());
        }
    }
}
