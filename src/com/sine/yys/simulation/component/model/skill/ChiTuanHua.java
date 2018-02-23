package com.sine.yys.simulation.component.model.skill;

import com.sine.yys.simulation.component.model.Controller;
import com.sine.yys.simulation.component.model.Entity;
import com.sine.yys.simulation.component.model.event.BeDamageEvent;
import com.sine.yys.simulation.component.model.event.BeforeActionEvent;
import com.sine.yys.simulation.event.EventHandler;
import com.sine.yys.simulation.info.AttackInfoImpl;
import com.sine.yys.simulation.util.Msg;

/**
 * 彼岸花-赤团华。
 */
public class ChiTuanHua extends BasePassiveSkill implements PassiveSkill {
    private XueZhiHuaHai xueZhiHuaHai = new XueZhiHuaHai();

    @Override
    public String getName() {
        return "赤团华";
    }

    public double getCoefficient() {
        return 0.39 * 1.25;
    }

    @Override
    public void init(Controller controller) {
        Entity self = controller.getSelf();
        controller.getEnemy().getEventController().add(new Handler(self));
        controller.getOwn().getEventController(self).add(new Handler2(self));
    }

    class Handler extends SealablePassiveHandler implements EventHandler<BeforeActionEvent> {
        Handler(Entity self) {
            super(self);
        }

        @Override
        public void handle(BeforeActionEvent event) {
            int level = xueZhiHuaHai.getLevel(self);
            if (!self.isDead() && level > 0) {
                log.info(Msg.trigger(self, ChiTuanHua.this));
                event.getController().attack(event.getEntity(), new AttackInfoImpl(getCoefficient() * level));
            }
        }
    }

    class Handler2 extends SealablePassiveHandler implements EventHandler<BeDamageEvent> {
        Handler2(Entity self) {
            super(self);
        }

        @Override
        public void handle(BeDamageEvent event) {
            int src = (int) (event.getSrc() * 4);
            int dst = (int) (event.getDst() * 4);
            if (src != 4 && src != dst) {
                log.info(Msg.trigger(self, self));
                xueZhiHuaHai.addLevel(self, src - dst);
                xueZhiHuaHai.addShield(self);
            }
        }
    }
}
