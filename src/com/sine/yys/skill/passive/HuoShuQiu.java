package com.sine.yys.skill.passive;

import com.sine.yys.event.BeAttackEvent;
import com.sine.yys.event.LongShouZhiYuOff;
import com.sine.yys.event.LongShouZhiYuOn;
import com.sine.yys.inter.Controller;
import com.sine.yys.inter.Entity;
import com.sine.yys.inter.EventHandler;
import com.sine.yys.inter.PctEffect;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

/**
 * 辉夜姬-火鼠裘。
 */
public class HuoShuQiu extends BasePassiveSkill implements EventHandler<BeAttackEvent>, PctEffect {
    @Override
    public String getName() {
        return "火鼠裘";
    }

    /**
     * 回火概率。
     */
    @Override
    public double getPct() {
        return 0.4;
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
        self.getEventController().add(LongShouZhiYuOn.class, event -> {
            self.getEventController().remove(this);
            getOwn().getEventController().add(0, this);
        });
        self.getEventController().add(LongShouZhiYuOff.class, event -> {
            getOwn().getEventController().remove(this);
            self.getEventController().add(0, this);
        });
    }

    @Override
    public void handle(BeAttackEvent event) {
        if (RandUtil.success(getPct())) {
            log.info(Msg.trigger(getSelf(), this));
            event.getEntity().getFireRepo().addFire(1);
        }
    }
}
