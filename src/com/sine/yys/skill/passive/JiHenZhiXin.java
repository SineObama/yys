package com.sine.yys.skill.passive;

import com.sine.yys.buff.debuff.FengYin;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.inter.*;

/**
 * 般若-嫉恨之心。
 */
public class JiHenZhiXin extends BasePassiveSkill implements PassiveSkill, EventHandler<AttackEvent>, DebuffEffect {
    @Override
    public String getName() {
        return "嫉恨之心";
    }

    public double getPct() {
        return 0.4;
    }

    @Override
    public boolean involveHitAndDef() {
        return true;
    }

    @Override
    public Debuff getDebuff(Entity self) {
        return new FengYin(getLast(), self);
    }

    /**
     * @return 封印持续回合数。
     */
    public int getLast() {
        return 2;
    }

    @Override
    public void handle(AttackEvent event) {
        getController().applyDebuff(getSelf(), event.getTarget(), this);
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }
}
