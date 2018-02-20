package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.CriticalEvent;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

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
    public void init(InitContext context) {
        context.getSelf().getEventController().add(new Handler(context.getSelf()));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<CriticalEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(CriticalEvent event) {
            if (RandUtil.success(getPct())) {
                log.info(Msg.trigger(self, ZhenNv.this));
                self.realDamage(event.getTarget(), getMaxDamageByAttack(), getMaxDamageByMaxLife());
            }
        }
    }
}
