package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.component.event.EventHandler;
import com.sine.yys.simulation.model.battle.InitContext;
import com.sine.yys.simulation.model.buff.debuff.FengYin;
import com.sine.yys.simulation.model.effect.PctEffect;
import com.sine.yys.simulation.model.event.AttackEvent;

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
        event.getSelf().applyDebuff(this, event.getTarget(), new FengYin(getLast()));
    }

    public void init(InitContext context) {
        context.getSelf().getEventController().add(this);
    }
}
