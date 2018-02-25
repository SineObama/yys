package com.sine.yys.mitama;

import com.sine.yys.buff.shield.BangJingShield;
import com.sine.yys.event.BattleStartEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.Mitama;
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
    public void init(Controller controller, Entity self) {
        controller.getCamp(self).getEventController().add(new Handler(self));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<BattleStartEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(BattleStartEvent event) {
            log.info(Msg.trigger(self, BangJing.this));
            final double value = self.getMaxLife() * getShieldByMaxLife();
            Controller controller = event.getController();
            for (Entity entity : controller.getCamp(self).getAllAlive()) {
                entity.getBuffController().add(new BangJingShield(controller.shieldValue(self, value)));
            }
        }
    }
}
