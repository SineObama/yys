package com.sine.yys.simulation.component.model.mitama;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.event.BattleStartEvent;
import com.sine.yys.simulation.event.EventHandler;
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
    public void init(Controller controller) {
        controller.getOwn().getEventController().add(new Handler(controller));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<BattleStartEvent> {
        Handler(Controller controller) {
            super(controller);
        }

        @Override
        public void handle(BattleStartEvent event) {
            log.info(Msg.trigger(self, HuoLing.this));
            self.getFireRepo().addFire(getFire());
        }
    }
}
