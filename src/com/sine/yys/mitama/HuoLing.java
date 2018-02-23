package com.sine.yys.mitama;

import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

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
