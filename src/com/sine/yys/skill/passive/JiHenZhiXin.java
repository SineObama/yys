package com.sine.yys.skill.passive;

import com.sine.yys.buff.debuff.FengYin;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.info.PctEffect;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

/**
 * 般若-嫉恨之心。
 */
public class JiHenZhiXin extends BasePassiveSkill implements PassiveSkill, PctEffect, EventHandler<AttackEvent> {
    @Override
    public String getName() {
        return "嫉恨之心";
    }

    @Override
    public double getPct() {
        return 0.4;
    }

    /**
     * @return 封印持续回合数。
     */
    public int getLast() {
        return 2;
    }

    @Override
    public void handle(AttackEvent event) {
        event.getController().applyDebuff(event.getEntity(), this, event.getTarget(), new FengYin(getLast()));
    }

    public void init(Controller controller, Entity self) {
        self.getEventController().add(this);
    }
}
