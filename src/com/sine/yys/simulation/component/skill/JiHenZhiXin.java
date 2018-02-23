package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.buff.debuff.FengYin;
import com.sine.yys.simulation.component.event.AttackEvent;
import com.sine.yys.simulation.component.inter.Controller;
import com.sine.yys.simulation.event.EventHandler;
import com.sine.yys.simulation.info.PctEffect;

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
        event.getController().applyDebuff(this, event.getTarget(), new FengYin(getLast()));
    }

    public void init(Controller controller) {
        controller.getEventController().add(this);
    }
}
