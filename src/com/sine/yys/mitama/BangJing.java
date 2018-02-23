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
public class BangJing extends BaseMitama implements Mitama {
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
    public void init(Controller controller) {
        controller.getOwn().getEventController().add(new Handler(controller));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<BattleStartEvent> {
        Handler(Controller controller) {
            super(controller);
        }

        @Override
        public void handle(BattleStartEvent event) {
            log.info(Msg.trigger(self, BangJing.this));
            final double value = self.getMaxLife() * getShieldByMaxLife();
            for (Entity entity : controller.getCamp(self).getAllAlive()) {
                entity.getBuffController().addShield(new BangJingShield(self.shieldValue(value)));
            }
        }
    }
}
