package com.sine.yys.mitama;

import com.sine.yys.buff.shield.DiZangXiangShield;
import com.sine.yys.event.BeCriticalEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 地藏像。
 */
public class DiZangXiang extends BaseSelfMitama implements EventHandler<BeCriticalEvent>, PctEffect {
    @Override
    public String getName() {
        return "地藏像";
    }

    /**
     * 为其他友方单位加盾的概率。
     */
    @Override
    public double getPct() {
        return 0.3;
    }

    /**
     * 生成一个能够吸收生命值百分比的伤害的护盾。
     */
    public double getShieldByMaxLife() {
        return 0.1;
    }

    @Override
    public void handle(BeCriticalEvent event) {
        final Entity self = getSelf();
        log.info(Msg.trigger(self, this));
        final double value = self.getMaxLife() * getShieldByMaxLife();
        Controller controller = getController();
        self.getBuffController().add(new DiZangXiangShield(controller.calcCritical(self, value), self));
        for (Entity entity : getOwn().getAllAlive()) {
            if (entity == self)
                continue;
            if (RandUtil.success(getPct())) {
                final DiZangXiangShield diZangXiangShield = new DiZangXiangShield(controller.calcCritical(self, value), self);
                log.info(Msg.addBuff(self, entity, diZangXiangShield));
                entity.getBuffController().add(diZangXiangShield);
            }
        }
    }
}
