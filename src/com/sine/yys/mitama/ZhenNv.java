package com.sine.yys.mitama;

import com.sine.yys.event.CriticalEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 针女。
 */
public class ZhenNv extends BaseSelfMitama implements EventHandler<CriticalEvent>, PctEffect {
    @Override
    public String getName() {
        return "针女";
    }

    @Override
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
            final Entity self = getSelf();
            log.info(Msg.trigger(self, this));
            final double damage1 = self.getAttack() * getMaxDamageByAttack();
            final double damage2 = event.getTarget().getMaxLife() * getMaxDamageByMaxLife();
            getController().realDamage(self, event.getTarget(), Double.min(damage1, damage2), event.getType());
        }
    }
}
