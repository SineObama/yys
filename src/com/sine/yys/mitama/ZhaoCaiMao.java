package com.sine.yys.mitama;

import com.sine.yys.event.ZhaoCaiMaoEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 招财猫。
 */
public class ZhaoCaiMao extends BaseMitama implements EventHandler<ZhaoCaiMaoEvent> {
    @Override
    public String getName() {
        return "招财猫";
    }

    public int getFire() {
        return 2;
    }

    public double getPct() {
        return 0.5;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    @Override
    public void handle(ZhaoCaiMaoEvent event) {
        if (RandUtil.success(getPct())) {
            final Entity self = getSelf();
            log.info(Msg.trigger(self, this));
            self.getFireRepo().addFire(getFire());
        }
    }
}
