package com.sine.yys.mitama;

import com.sine.yys.buff.shield.BangJingShield;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 蚌精。
 */
public class BangJing extends BaseMitama implements EventHandler<BattleStartEvent> {
    @Override
    public String getName() {
        return "蚌精";
    }

    /**
     * 生成一个能够吸收生命值百分比的伤害的护盾。
     */
    public double getShieldByMaxLife() {
        return 0.1;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        getOwn().getEventController().add(this);
    }

    @Override
    public void handle(BattleStartEvent event) {
        final Entity self = getSelf();
        log.info(Msg.trigger(self, this));
        final double value = self.getMaxLife() * getShieldByMaxLife();
        for (Entity entity : getOwn().getAllAlive()) {
            entity.getBuffController().add(new BangJingShield(getController().calcCritical(self, value), self));
        }
    }
}
