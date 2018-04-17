package com.sine.yys.mitama;

import com.sine.yys.event.BeAttackEvent;
import com.sine.yys.event.EnterEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 木魅。
 */
public class MuMei extends BaseMitama implements EventHandler<BeAttackEvent>, PctEffect {
    @Override
    public String getName() {
        return "木魅";
    }

    /**
     * 减少对方鬼火的概率。
     */
    @Override
    public double getPct() {
        return 0.25;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        getOwn().getEventController().add(EnterEvent.class, (event -> event.getEntity().getEventController().add(0, MuMei.this)));
    }

    @Override
    public void handle(BeAttackEvent event) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            event.getTarget().getFireRepo().grabFire(1);
        }
    }
}
