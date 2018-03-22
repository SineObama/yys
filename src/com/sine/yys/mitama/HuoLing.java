package com.sine.yys.mitama;

import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 火灵。
 */
public class HuoLing extends BaseMitama implements EventHandler<BattleStartEvent> {
    @Override
    public String getName() {
        return "火灵";
    }

    public int getAddFire() {
        return 3;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        getOwn().getEventController().add(this);
    }

    @Override
    public void handle(BattleStartEvent event) {
        final Entity self = getSelf();
        log.info(Msg.trigger(self, this));
        self.getFireRepo().addFire(getAddFire());
    }
}
