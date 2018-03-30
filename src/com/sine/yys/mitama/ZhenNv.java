package com.sine.yys.mitama;

import com.sine.yys.event.CriticalEvent;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 针女。
 * <p>
 * 1. 可以被涓流或薙魂分担；
 * 2. 不被金鱼分担；
 * 3. 不受一般buff影响，只受旗帜buff增伤影响。
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
            if (event.getTarget().isDead())
                return;
            final Entity self = getSelf();
            log.info(Msg.trigger(self, this));
            event.getType().setZhenNv(true);
            final double damage1 = self.getAttack() * getMaxDamageByAttack();
            final double damage2 = event.getTarget().getMaxLife() * getMaxDamageByMaxLife();
            double damage = Double.min(damage1, damage2);
            // 根据旗帜buff增减。
            damage *= self.getFlagDamageCoefficient();
            getController().applyDamage(self, event.getTarget(), damage, false, event.getType());
        }
    }
}
