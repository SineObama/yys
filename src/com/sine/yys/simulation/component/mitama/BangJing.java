package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.Mitama;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.InitContext;
import com.sine.yys.simulation.component.model.event.BattleStartEvent;
import com.sine.yys.simulation.component.model.shield.BangJingShield;
import com.sine.yys.simulation.util.Msg;

/**
 * 蚌精。
 */
public class BangJing extends BaseMitama implements Mitama, EventHandler<BattleStartEvent> {
    @Override
    public String getName() {
        return "蚌精";
    }

    /**
     * 生成一个能够吸收生命值***的伤害的护盾。
     */
    public double getShieldByMaxLife() {
        return 0.1;
    }

    @Override
    public void handle(BattleStartEvent event) {
        log.info(Msg.trigger(getSelf(), this));
        final int value = (int) (getSelf().getMaxLife() * getShieldByMaxLife());
        for (Entity entity : getSelf().getCamp().getAllAlive()) {
            entity.getBuffController().addShield(new BangJingShield(value));
        }
    }

    @Override
    public void doInit(InitContext context) {
        context.getOwn().getEventController().add(this);
    }
}
