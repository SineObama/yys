package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.model.EventHandler;
import com.sine.yys.simulation.model.InitContext;
import com.sine.yys.simulation.model.Mitama;
import com.sine.yys.simulation.model.event.CriticalEvent;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 针女。
 */
public class ZhenNv extends BaseMitama implements Mitama, EventHandler<CriticalEvent> {
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
    public void handle(CriticalEvent event) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            getSelf().realDamage(event.getTarget(), getMaxDamageByAttack(), getMaxDamageByMaxLife());
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getSelf().getEventController().add(this);
    }
}
