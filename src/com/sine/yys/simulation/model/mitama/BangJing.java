package com.sine.yys.simulation.model.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.event.BattleStartEvent;
import com.sine.yys.simulation.component.event.BeCriticalEvent;
import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.shield.BangJingShield;
import com.sine.yys.simulation.model.shield.DiZangXiangShield;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

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
    public void handle(BattleStartEvent context, Controller controller) {
        log.info(Msg.trigger(this));
        final int value = (int) (getSelf().getMaxLife() * getShieldByMaxLife());
        for (Entity entity : getSelf().getCamp().getAllAlive()) {
            entity.getBuffController().addShield(new BangJingShield(value));
        }
    }

    @Override
    public void init(InitContext context) {
        super.init(context);
        context.getOwn().getEventController().add(BattleStartEvent.class, this);
    }
}
