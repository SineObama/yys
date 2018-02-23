package com.sine.yys.mitama;

import com.sine.yys.event.CriticalEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 针女。
 */
public class ZhenNv extends BaseMitama implements Mitama {
    @Override
    public String getName() {
        return "针女";
    }

    public double getPct() {
        return 0.4;
    }

    /**
     * 最多造成自身攻击***的伤害。
     */
    public double getMaxDamageByAttack() {
        return 1.2;
    }

    /**
     * 造成对方血量***的伤害。
     */
    public double getMaxDamageByMaxLife() {
        return 0.1;
    }

    @Override
    public void init(Controller controller) {
        controller.getEventController().add(new Handler(controller));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<CriticalEvent> {
        Handler(Controller controller) {
            super(controller);
        }

        @Override
        public void handle(CriticalEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(self, ZhenNv.this));
                controller.realDamage(event.getTarget(), getMaxDamageByAttack(), getMaxDamageByMaxLife());
            }
        }
    }
}