package com.sine.yys.mitama;

import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.Mitama;
import com.sine.yys.util.Msg;

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
    public void doInit(Controller controller, Entity self) {
        controller.getCamp(self).getEventController().add(this);
    }

    @Override
    public void handle(BattleStartEvent event) {
        final Entity self = getSelf();
        log.info(Msg.trigger(self, HuoLing.this));
        self.getFireRepo().addFire(getFire());
    }
}
