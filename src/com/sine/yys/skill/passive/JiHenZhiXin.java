package com.sine.yys.skill.passive;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.buff.debuff.DispellableDebuff;
import com.sine.yys.buff.debuff.SealMitama;
import com.sine.yys.buff.debuff.SealPassive;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.inter.*;

/**
 * 般若-嫉恨之心。
 */
public class JiHenZhiXin extends BasePassiveSkill implements EventHandler<AttackEvent>, DebuffEffect {
    @Override
    public double getPct() {
        return 0.4;
    }

    public int getLast() {
        return 2;
    }

    @Override
    public String getName() {
        return "嫉恨之心";
    }

    @Override
    public void handle(AttackEvent event) {
        getController().applyDebuff(getSelf(), event.getTarget(), this);
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
    }

    public class FengYin extends BaseIBuff implements SealPassive, SealMitama, DispellableDebuff {
        FengYin(int last, String name, Entity src) {
            super(last, name + "-封印御魂和被动", src);
        }
    }

    @Override
    public IBuff getDebuff(Entity self) {
        return new FengYin(getLast(), getName(), self);
    }
}
