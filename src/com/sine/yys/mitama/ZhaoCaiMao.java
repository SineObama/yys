package com.sine.yys.mitama;

import com.sine.yys.event.ZhaoCaiMaoEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 招财猫。
 */
public class ZhaoCaiMao extends BaseSelfMitama implements EventHandler<ZhaoCaiMaoEvent>, PctEffect {
    @Override
    public String getName() {
        return "招财猫";
    }

    public int getAddFire() {
        return 2;
    }

    @Override
    public double getPct() {
        return 0.5;
    }

    @Override
    public void handle(ZhaoCaiMaoEvent event) {
        if (RandUtil.success(getPct())) {
            final Entity self = getSelf();
            log.info(Msg.trigger(self, this));
            self.getFireRepo().addFire(getAddFire());
        }
    }
}
