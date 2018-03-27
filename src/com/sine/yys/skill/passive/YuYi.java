package com.sine.yys.skill.passive;

import com.sine.yys.event.BeDamageEvent;
import com.sine.yys.inter.AttackType;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.util.Msg;

/**
 * 童女-羽衣。
 * <p>
 * 规则：
 * 1. 对毒伤生效；
 * 2. 被薙魂后触发（所以两者受伤不一样）；
 * 3. 在薙魂时也能触发（受伤又不一样）；
 * 4. 对涓流分摊的伤害无效（游戏中只显示动画没有被动图标，数值也一样）；
 * 5. *暂定涓流下被击独自生效，不影响其他人；
 * 6. *暂定对针女无效；
 */
public class YuYi extends BasePassiveSkill implements EventHandler<BeDamageEvent> {
    @Override
    public String getName() {
        return "羽衣";
    }

    /**
     * @return 吸收生命百分比。
     */
    public double getAbsorbLifePct() {
        return 0.03 * 1.6667;
    }

    /**
     * @return 最多吸收伤害的百分比。
     */
    public double getMaxAbsorbDamgePct() {
        return 0.4;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    @Override
    public void handle(BeDamageEvent event) {
        final AttackType type = event.getType();
        if (type.isJuanLiu() || type.isZhenNv())
            return;
        log.info(Msg.trigger(getSelf(), this));
        final double damage1 = getMaxAbsorbDamgePct() * event.getDamage();
        final double damage2 = getAbsorbLifePct() * getSelf().getMaxLife();
        event.setDamage(Double.min(damage1, damage2));
    }
}
