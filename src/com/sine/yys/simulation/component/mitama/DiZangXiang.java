package com.sine.yys.simulation.component.mitama;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.event.BeCriticalEvent;
import com.sine.yys.simulation.component.model.shield.DiZangXiangShield;
import com.sine.yys.simulation.util.Msg;
import com.sine.yys.simulation.util.RandUtil;

/**
 * 地藏像。
 */
public class DiZangXiang extends BaseMitama implements Mitama {
    @Override
    public String getName() {
        return "地藏像";
    }

    /**
     * 为其他友方单位加盾的概率。
     */
    public double getPct() {
        return 0.3;
    }

    /**
     * 生成一个能够吸收生命值***的伤害的护盾。
     */
    public double getShieldByMaxLife() {
        return 0.1;
    }

    @Override
    public void init(Controller controller) {
        controller.getEventController().add(new Handler(controller));
    }

    class Handler extends SealableMitamaHandler implements EventHandler<BeCriticalEvent> {
        Handler(Controller controller) {
            super(controller);
        }

        @Override
        public void handle(BeCriticalEvent event) {
            log.info(Msg.trigger(self, DiZangXiang.this));
            final double value = self.getMaxLife() * getShieldByMaxLife();
            self.getBuffController().addShield(new DiZangXiangShield(self.shieldValue(value)));
            for (Entity entity : self.getCamp().getAllAlive()) {
                if (entity == self)
                    continue;
                if (RandUtil.success(getPct())) {
                    final DiZangXiangShield diZangXiangShield = new DiZangXiangShield(self.shieldValue(value));
                    log.info(Msg.addBuff(self, entity, diZangXiangShield));
                    entity.getBuffController().addShield(diZangXiangShield);
                }
            }
        }
    }
}
