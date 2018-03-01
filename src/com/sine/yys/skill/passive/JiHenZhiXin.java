package com.sine.yys.skill.passive;

import com.sine.yys.effect.FengYinEffect;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;

/**
 * 般若-嫉恨之心。
 */
public class JiHenZhiXin extends BasePassiveSkill implements PassiveSkill, EventHandler<AttackEvent> {
    private DebuffEffect effect = new FengYinEffect(getPct(), getLast(), getName());

    @Override
    public String getName() {
        return "嫉恨之心";
    }

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
        getController().applyDebuff(event.getEntity(), event.getTarget(), effect);
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }
}
