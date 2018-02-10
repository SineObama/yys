package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.CriticalEvent;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
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
    public void handle(CriticalEvent context, Controller controller) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(this));
            controller.realDamage(context.getSelf(), context.getTarget(), getMaxDamageByAttack(), getMaxDamageByMaxLife());
        }
    }

    @Override
    public void init(InitContext context) {
        super.init(context);
        context.getSelf().getEventController().add(CriticalEvent.class, this);
    }
}
